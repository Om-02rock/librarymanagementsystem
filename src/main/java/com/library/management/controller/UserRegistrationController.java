package com.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.library.management.entity.User;
import com.library.management.service.UserService;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") User user, BindingResult result) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            result.rejectValue("email", "NotEmpty", "Email is required");
        } else {
            User existing = userService.findUserByEmail(user.getEmail());
            if (existing != null) {
                result.rejectValue("email", "Duplicate", "An account is already registered with this email");
            }
        }

        if (result.hasErrors()) {
            return "registration";
        }
        userService.registerUser(user);
        return "redirect:/login?success";
    }
}

