package com.himeshnayak.hiremenow.controller;

import org.springframework.ui.Model;

import com.himeshnayak.hiremenow.model.User;
import com.himeshnayak.hiremenow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(@CookieValue(value = "userId", defaultValue = "invalid") String userId, Model model) {

        if(!userId.equals("invalid")) {
            User user = userService.getUserById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/login";
        }

        return "profile";
    }



}
