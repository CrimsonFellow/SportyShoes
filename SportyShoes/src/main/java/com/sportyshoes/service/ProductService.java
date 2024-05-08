package com.sportyshoes.service;

import com.sportyshoes.model.Product;
import com.sportyshoes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    // Save a new product
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Find a product by ID
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    // Delete a product
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
