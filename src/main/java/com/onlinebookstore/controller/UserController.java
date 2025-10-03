package com.onlinebookstore.controller;

import com.onlinebookstore.Services.UserService;
import com.onlinebookstore.entity.UserDTO;
import com.onlinebookstore.exceptionhandling.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/createuser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws CustomException {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/getalluser")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/getuserbyid/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) throws CustomException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateuser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws CustomException {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping("/forgetpassword")
    public ResponseEntity<String> forgetPassword(@RequestBody String data) throws CustomException {
        return ResponseEntity.ok(userService.forgetPassword(data));
    }
}
