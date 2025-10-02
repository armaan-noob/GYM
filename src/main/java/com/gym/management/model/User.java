package com.gym.management.model;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String userRole;
    
    public User(Integer id, String name, String email, String userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getUserRole() {
        return userRole;
    }
    
    public boolean isAdmin() {
        return "ADMIN".equals(userRole);
    }
}