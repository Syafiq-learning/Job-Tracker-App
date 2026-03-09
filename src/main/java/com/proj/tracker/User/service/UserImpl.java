package com.proj.tracker.User.service;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

import com.proj.tracker.User.model.User;
import com.proj.tracker.User.repository.UserRepo;

@Service
public class UserImpl implements UserService{
    private final UserRepo userRepository;

    public UserImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(String id, User user) {
        User existing = userRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(user.getName());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());

            return userRepository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
