package com.gym.management.model;

import java.time.LocalDate;

public class Member {
    private int id;
    private String name;
    private int age;
    private String contact;
    private String email;
    private String membershipType;
    private LocalDate joinDate;
    private String userRole; // "ADMIN" or "MEMBER"
    private String password;
    
    // Constructors
    public Member() {}
    
    public Member(String name, int age, String contact, String email, 
                 String membershipType, String userRole, String password) {
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.email = email;
        this.membershipType = membershipType;
        this.joinDate = LocalDate.now();
        this.userRole = userRole;
        this.password = password;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}