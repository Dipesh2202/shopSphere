package com.shopsphere.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="customer_orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String customerName;
    private String email;
    @Column(length=1200) private String address;
    private double total;
    private String status = "PLACED";
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(length=4000) private String itemsSummary;
    public CustomerOrder() {}
    public CustomerOrder(String name,String email,String address,double total,String summary) {
        this.customerName=name; this.email=email; this.address=address; this.total=total; this.itemsSummary=summary;
    }
    public Long getId(){return id;} public String getCustomerName(){return customerName;}
    public String getEmail(){return email;} public String getAddress(){return address;}
    public double getTotal(){return total;} public String getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;} public String getItemsSummary(){return itemsSummary;}
}
