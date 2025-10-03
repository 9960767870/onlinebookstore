package com.onlinebookstore.controller;

import com.onlinebookstore.Services.UserService;
import com.onlinebookstore.entity.AuthRequest;
import com.onlinebookstore.entity.AuthRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthRequestResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.login(authRequest));
    }
}
