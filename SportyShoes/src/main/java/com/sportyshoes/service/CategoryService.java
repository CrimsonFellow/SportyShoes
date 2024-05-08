package com.sportyshoes.service;

import com.sportyshoes.model.Category;
import com.sportyshoes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Save a new category
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // Find a category by ID
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Delete a category
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
