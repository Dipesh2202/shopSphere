package com.shopsphere.repository;
import com.shopsphere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
}
