package com.sportyshoes.repository;

import com.sportyshoes.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o JOIN o.product p JOIN p.category c WHERE o.orderDate = :orderDate AND c.name = :categoryName")
    List<Order> findOrdersByDateAndCategory(
            @Param("orderDate") LocalDate orderDate, 
            @Param("categoryName") String categoryName);
}


