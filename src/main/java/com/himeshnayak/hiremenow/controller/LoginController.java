package com.himeshnayak.hiremenow.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.himeshnayak.hiremenow.model.User;
import com.himeshnayak.hiremenow.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    private UserService userService;

    @Autowired
    public LoginController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(@CookieValue(value = "userId", defaultValue = "invalid") String cookieUserId, Model model) {

        if (cookieUserId.equals("invalid")) {
            User loginUser = new User();
            model.addAttribute("loginUser", loginUser);
        } else {
            return "redirect:/profile";
        }
        return "login";
    }

    @PostMapping("/login") 
	public String loginSuccess(@ModelAttribute("loginUser") User loginUser, HttpServletResponse response) {

        User userInDB = userService.getUserByName(loginUser.getName());
        if (userInDB != null) {
            if (loginUser.getPassword().equals(userInDB.getPassword())) {
                String userId = userInDB.getUUID().toString();
                String name = userInDB.getName().replace(' ', '_');
                String type = userInDB.getType();
                response.addCookie(new Cookie("userId", userId));
                response.addCookie(new Cookie("name", name));
                response.addCookie(new Cookie("type", type));
                return "redirect:/profile";
            } else {
                System.out.println("Password did not match");
            }
        } else {
            System.out.println("No user found with this name");
        }

		return "login";
	}

}
