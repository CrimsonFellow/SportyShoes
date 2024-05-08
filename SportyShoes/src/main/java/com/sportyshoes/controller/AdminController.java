package com.sportyshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sportyshoes.service.AdminService;
import com.sportyshoes.service.OrderService;
import com.sportyshoes.model.Admin;
import com.sportyshoes.service.UserService;

import jakarta.servlet.http.HttpSession;

import com.sportyshoes.model.User;

import java.time.LocalDate;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;
    
    @Autowired
    private OrderService orderService;



    @GetMapping("/login")  // Handle GET request for the login page
    public String showLoginPage() {
        return "admin_login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes attributes, HttpSession session) {
        Admin admin = adminService.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {  // Plain text comparison, consider using password hashing
            session.setAttribute("loggedInUsername", username);  // Store the username in the session
            return "redirect:/admin/dashboard";
        } else {
            attributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/admin/login";
        }
    }


    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin_dashboard";
    }
    
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";  // This should match the name of your Thymeleaf template for displaying users
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/admin/users?error=UserNotFound";
        }
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, @ModelAttribute User user, RedirectAttributes attributes) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            attributes.addFlashAttribute("error", "User not found");
            return "redirect:/admin/users";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        }
        userService.save(user);
        attributes.addFlashAttribute("success", "User updated successfully!");
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Integer id, RedirectAttributes attributes) {
        userService.deleteById(id);
        attributes.addFlashAttribute("success", "User deleted successfully!");
        return "redirect:/admin/users";
    }
    
    @GetMapping("/reports")
    public String viewReports(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate,
            String category,
            Model model) {
        model.addAttribute("orders", orderService.getOrdersByDateAndCategory(orderDate, category));
        return "reportView"; // Thymeleaf template for displaying reports
    }
    @PostMapping("/changePassword")
    public String changeAdminPassword(@RequestParam("currentPassword") String currentPassword,
                                      @RequestParam("newPassword") String newPassword,
                                      RedirectAttributes attributes,
                                      HttpSession session) {
        try {
            Long adminId = getLoggedInAdminId(session);  // Pass the session to retrieve the ID
            Admin admin = adminService.findById(adminId);
            
            // Verify current password matches before changing
            if (!admin.getPassword().equals(currentPassword)) {
                attributes.addFlashAttribute("error", "Current password is incorrect.");
                return "redirect:/admin/dashboard";
            }

            adminService.updateAdminPassword(adminId, newPassword);
            attributes.addFlashAttribute("success", "Password updated successfully!");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "Failed to update password: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }
    private Long getLoggedInAdminId(HttpSession session) {
        String username = (String) session.getAttribute("loggedInUsername");
        if (username == null) {
            throw new IllegalStateException("No admin is currently logged in.");
        }
        Admin admin = adminService.findByUsername(username);
        return admin.getId();  // Assuming that the ID is stored as Long
    }
}










