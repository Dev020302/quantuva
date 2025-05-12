
package com.ticketing.controller;

import com.ticketing.dto.LoginRequest;
import com.ticketing.dto.RegisterRequest;
import com.ticketing.dto.UserDto;
import com.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping("/agents")
    public ResponseEntity<List<UserDto>> getAllAgents() {
        return ResponseEntity.ok(userService.getAllAgents());
    }
}