package com.sportyshoes.service;

import com.sportyshoes.model.Order;
import com.sportyshoes.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByDateAndCategory(LocalDate orderDate, String category) {
        return orderRepository.findOrdersByDateAndCategory(orderDate, category);
    }
}


