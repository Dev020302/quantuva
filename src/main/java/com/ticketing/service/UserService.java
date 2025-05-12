
package com.ticketing.service;

import com.ticketing.dto.LoginRequest;
import com.ticketing.dto.RegisterRequest;
import com.ticketing.dto.UserDto;
import com.ticketing.model.User;
import com.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());  // No encryption as per requirements
        user.setRole(registerRequest.getRole());

        User savedUser = userRepository.save(user);
        return UserDto.fromUser(savedUser);
    }

    public UserDto login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();

        // Simple password check without encryption
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return UserDto.fromUser(user);
    }

    public List<UserDto> getAllAgents() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == User.UserRole.AGENT)
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Create default admin and agents during application startup
    public void createDefaultUsers() {
        if (!userRepository.existsByEmail("admin@support.com")) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@support.com");
            admin.setPassword("admin");
            admin.setRole(User.UserRole.ADMIN);
            userRepository.save(admin);
        }

        if (!userRepository.existsByEmail("agent1@support.com")) {
            User agent1 = new User();
            agent1.setName("Agent 1");
            agent1.setEmail("agent1@support.com");
            agent1.setPassword("1234");
            agent1.setRole(User.UserRole.AGENT);
            userRepository.save(agent1);
        }

        if (!userRepository.existsByEmail("agent2@support.com")) {
            User agent2 = new User();
            agent2.setName("Agent 2");
            agent2.setEmail("agent2@support.com");
            agent2.setPassword("1234");
            agent2.setRole(User.UserRole.AGENT);
            userRepository.save(agent2);
        }
    }
}