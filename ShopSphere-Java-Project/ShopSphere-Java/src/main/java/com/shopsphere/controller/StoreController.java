package com.shopsphere.controller;

import com.shopsphere.model.CustomerOrder;
import com.shopsphere.model.Product;
import com.shopsphere.repository.OrderRepository;
import com.shopsphere.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class StoreController {
    private final ProductRepository products;
    private final OrderRepository orders;
    public StoreController(ProductRepository products, OrderRepository orders) {
        this.products=products; this.orders=orders;
    }
    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        return cart(session).values().stream().mapToInt(Integer::intValue).sum();
    }
    @SuppressWarnings("unchecked")
    private Map<Long,Integer> cart(HttpSession session) {
        Object value=session.getAttribute("cart");
        if(value instanceof Map) return (Map<Long,Integer>) value;
        Map<Long,Integer> fresh=new LinkedHashMap<>(); session.setAttribute("cart",fresh); return fresh;
    }
    @GetMapping("/")
    public String home(@RequestParam(required=false) String q, Model model) {
        List<Product> list=(q==null || q.isBlank()) ? products.findAll() :
            products.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(q.trim(),q.trim());
        model.addAttribute("products",list); model.addAttribute("q",q==null?"":q);
        return "index";
    }
    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model) {
        Product p=products.findById(id).orElse(null);
        if(p==null) return "redirect:/";
        model.addAttribute("product",p); return "product";
    }
    @PostMapping("/cart/add/{id}")
    public String add(@PathVariable Long id, HttpSession session, RedirectAttributes flash) {
        Product p=products.findById(id).orElse(null);
        if(p==null) { flash.addFlashAttribute("error","That product could not be found."); return "redirect:/"; }
        Map<Long,Integer> c=cart(session);
        c.put(id,Math.min(c.getOrDefault(id,0)+1,Math.max(1,p.getStock())));
        flash.addFlashAttribute("notice",p.getName()+" added to your cart.");
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String cartPage(HttpSession session, Model model) {
        List<Map<String,Object>> lines=new ArrayList<>(); double total=0;
        for(var entry:cart(session).entrySet()) {
            Product p=products.findById(entry.getKey()).orElse(null);
            if(p!=null) {
                double subtotal=p.getPrice()*entry.getValue(); total+=subtotal;
                Map<String,Object> line=new HashMap<>(); line.put("product",p); line.put("quantity",entry.getValue()); line.put("subtotal",subtotal); lines.add(line);
            }
        }
        model.addAttribute("lines",lines); model.addAttribute("total",total); return "cart";
    }
    @PostMapping("/cart/update")
    public String update(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
        Map<Long,Integer> c=cart(session);
        if(quantity<=0) c.remove(productId);
        else {
            Product p=products.findById(productId).orElse(null);
            if(p!=null) c.put(productId,Math.min(quantity,Math.max(1,p.getStock())));
        }
        return "redirect:/cart";
    }
    @PostMapping("/cart/remove/{id}")
    public String remove(@PathVariable Long id,HttpSession session) { cart(session).remove(id); return "redirect:/cart"; }
    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        if(cart(session).isEmpty()) return "redirect:/cart";
        fillCart(session,model); return "checkout";
    }
    private void fillCart(HttpSession session,Model model) {
        double total=0;
        for(var e:cart(session).entrySet()) {
            Product p=products.findById(e.getKey()).orElse(null);
            if(p!=null) total+=p.getPrice()*e.getValue();
        }
        model.addAttribute("total",total);
    }
    @PostMapping("/checkout")
    public String placeOrder(@RequestParam @NotBlank String customerName,
                             @RequestParam @Email @NotBlank String email,
                             @RequestParam @NotBlank String address,
                             HttpSession session, Model model) {
        Map<Long,Integer> c=cart(session);
        if(c.isEmpty()) return "redirect:/cart";
        double total=0; StringBuilder summary=new StringBuilder();
        for(var e:c.entrySet()) {
            Product p=products.findById(e.getKey()).orElse(null);
            if(p!=null) { total+=p.getPrice()*e.getValue(); summary.append(p.getName()).append(" x ").append(e.getValue()).append("; "); }
        }
        CustomerOrder order=orders.save(new CustomerOrder(customerName,email,address,total,summary.toString()));
        c.clear();
        model.addAttribute("order",order);
        return "order-success";
    }
}
