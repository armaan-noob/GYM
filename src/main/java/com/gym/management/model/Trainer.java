package com.gym.management.model;

import java.util.Date;

public class Trainer {
    private Integer id;
    private String name;
    private String email;
    private String specialization;
    private Integer experienceYears;
    private String status;
    private Date createdAt;

    // Constructors
    public Trainer() {}

    public Trainer(String name, String email, String specialization, Integer experienceYears) {
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.status = "Available";
        this.createdAt = new Date();
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}