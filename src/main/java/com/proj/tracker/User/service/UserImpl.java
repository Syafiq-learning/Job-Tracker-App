package com.proj.tracker.User.service;


import java.util.List;

import com.proj.tracker.User.dto.RegisterRequest;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public User updateUser(String id, User user) {
        User existing = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

       
        existing.setName(user.getName());
        
        String email = user.getEmail().trim().toLowerCase();
        if (!email.equals(existing.getEmail()) && userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        existing.setEmail(email);
    

        return userRepository.save(existing);

      
    }

    @Override
    public User register(RegisterRequest request) {

        String email = request.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        if(!request.password().equals(request.confirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        String hash = passwordEncoder.encode(request.password());
        return userRepository.save(new User(request.name(), email, hash));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
