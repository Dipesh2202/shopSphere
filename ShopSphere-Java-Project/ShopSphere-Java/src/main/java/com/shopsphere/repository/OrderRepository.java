package com.shopsphere.repository;
import com.shopsphere.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<CustomerOrder,Long> {}
