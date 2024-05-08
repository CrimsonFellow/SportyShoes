package com.sportyshoes.controller;

import com.sportyshoes.model.Product;
import com.sportyshoes.service.ProductService;
import com.sportyshoes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // List all products
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "manage_products";
    }

    // Show the form for adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "add_product";
    }

    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    // Show the form for editing an existing product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "edit_product";
    }

    // Process the form for updating an existing product
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productService.save(product); 
        return "redirect:/admin/products";
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}
