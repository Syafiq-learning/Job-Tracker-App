package com.proj.tracker;

import com.proj.tracker.User.dto.RegisterRequest;
import com.proj.tracker.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterRequest request,
                               BindingResult result,
                               Model model) {
        model.addAttribute("name", request.name());
        model.addAttribute("email", request.email());

        if (result.hasErrors()) {
            model.addAttribute("error", "Fill in all fields. Password must be at least 8 characters.");
            return "register";
        }
        try {
            userService.register(request);
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            return "register";
        }
        return "redirect:/login?registered";
    }
}
