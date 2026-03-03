package com.proj.tracker.User.service;

import com.proj.tracker.User.model.User;
import com.proj.tracker.User.repository.UserRepo;

public class UserImpl {
    private final UserRepo userRepository;

    public UserImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
}
