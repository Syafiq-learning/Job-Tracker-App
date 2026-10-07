package com.proj.tracker.User.controller;

import com.proj.tracker.User.dto.RegisterRequest;
import com.proj.tracker.User.dto.UserResponse;
import com.proj.tracker.User.model.User;
import com.proj.tracker.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return toResponse(userService.register(request));
    }
    


    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        return toResponse(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody User user) {
        return toResponse(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
