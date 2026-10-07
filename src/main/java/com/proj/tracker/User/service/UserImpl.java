package com.proj.tracker.User.service;


import java.util.List;

import com.proj.tracker.User.dto.RegisterRequest;
import com.proj.tracker.config.PasswordConfig;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.proj.tracker.User.model.User;
import com.proj.tracker.User.repository.UserRepo;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserImpl implements UserService{
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserImpl(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User register(RegisterRequest request) {
        if (request.email() == null || request.password() == null || request.password().length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email or password (min 8 chars)");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        String hash = passwordEncoder.encode(request.password());
        return userRepository.save(new User(request.name(), request.email(), hash.toCharArray()));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
