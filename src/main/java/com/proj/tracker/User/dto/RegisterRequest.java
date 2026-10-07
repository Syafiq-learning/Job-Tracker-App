package com.proj.tracker.User.dto;

public record RegisterRequest(String name, String email, String password, String confirmPassword) {
}
