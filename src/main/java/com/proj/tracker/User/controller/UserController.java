package com.proj.tracker.User.controller;

import com.proj.tracker.User.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
