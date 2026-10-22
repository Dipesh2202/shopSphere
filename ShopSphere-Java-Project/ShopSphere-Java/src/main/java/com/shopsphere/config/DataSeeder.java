package com.shopsphere.config;

import com.shopsphere.model.Product;
import com.shopsphere.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean CommandLineRunner seedProducts(ProductRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Product("Studio Wireless Headphones","Immersive sound, soft ear cushions and all-day comfort.","Electronics",2499,"https://images.unsplash.com/photo-1505740420928-5e560c06d30e",20));
                repo.save(new Product("Everyday Smart Watch","A clean design with fitness-inspired everyday features.","Electronics",3299,"https://images.unsplash.com/photo-1523275335684-37898b6baf30",15));
                repo.save(new Product("Minimal Backpack","Roomy, durable backpack for work, college and travel.","Fashion",1499,"https://images.unsplash.com/photo-1553062407-98eeb64c6a62",25));
                repo.save(new Product("Classic Sneakers","Versatile low-top sneakers for your everyday rotation.","Fashion",2199,"https://images.unsplash.com/photo-1542291026-7eec264c27ff",18));
                repo.save(new Product("Desk Lamp","Warm adjustable lighting for your study or work desk.","Home",899,"https://images.unsplash.com/photo-1507473885765-e6ed057f782c",30));
                repo.save(new Product("Ceramic Coffee Set","Simple ceramic cups to make your coffee ritual special.","Home",699,"https://images.unsplash.com/photo-1514228742587-6b1558fcca3d",40));
                repo.save(new Product("Portable Speaker","Compact design with room-filling sound for your playlist.","Electronics",1799,"https://images.unsplash.com/photo-1608043152269-423dbba4e7e1",12));
                repo.save(new Product("Modern Sunglasses","Lightweight frame with a timeless silhouette.","Fashion",999,"https://images.unsplash.com/photo-1511499767150-a48a237f0083",22));
            }
        };
    }
}
