package com.sportyshoes.service;

import com.sportyshoes.model.User;
import com.sportyshoes.service.UserService;
import com.sportyshoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save or update a user
    public User save(User user) {
        return userRepository.save(user);
    }

    // Fetch all users
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // Search users by username or email
    @Transactional(readOnly = true)
    public List<User> searchUsers(String keyword) {
        return userRepository.findByUsernameContainingOrEmailContainingAllIgnoreCase(keyword, keyword);
    }
    
 // Method to find a user by ID
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null); // Return the user if found, otherwise return null
    }

    // Method to delete a user by ID
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}


