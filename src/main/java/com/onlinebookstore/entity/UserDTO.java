package com.onlinebookstore.entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {

    private int userId;
    private String userName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private Role role;
    private Integer roleId;

    public UserDTO(int userId, String userName, String email, String password, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 /*   public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }*/

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDTO() {
    }
}
