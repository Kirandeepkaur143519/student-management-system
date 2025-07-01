package com.project.studentcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This will serve login.html from templates
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";  // This will serve access-denied.html
    }
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/students";
    }
}