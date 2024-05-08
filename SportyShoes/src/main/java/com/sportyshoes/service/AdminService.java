package com.sportyshoes.service;

import com.sportyshoes.model.Admin;
import com.sportyshoes.repository.AdminRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
    public void updateAdminPassword(Long adminId, String newPassword) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setPassword(newPassword);  
        adminRepository.save(admin);
    }
    public Admin findById(Long id) {
        // Using Optional to handle the case where an admin might not be found
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElseThrow(() -> new IllegalArgumentException("No admin found with ID: " + id));
    }
    
}
