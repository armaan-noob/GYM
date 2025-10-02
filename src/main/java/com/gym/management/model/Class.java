package com.gym.management.model;

import java.sql.Time;
import java.util.Date;

public class Class {
    private Integer id;
    private String name;
    private Integer trainerId;
    private Time scheduleTime;
    private String scheduleDay;
    private Integer capacity;
    private Date createdAt;

    // Constructors
    public Class() {}

    public Class(String name, Integer trainerId, Time scheduleTime, String scheduleDay, Integer capacity) {
        this.name = name;
        this.trainerId = trainerId;
        this.scheduleTime = scheduleTime;
        this.scheduleDay = scheduleDay;
        this.capacity = capacity;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getTrainerId() { return trainerId; }
    public void setTrainerId(Integer trainerId) { this.trainerId = trainerId; }

    public Time getScheduleTime() { return scheduleTime; }
    public void setScheduleTime(Time scheduleTime) { this.scheduleTime = scheduleTime; }

    public String getScheduleDay() { return scheduleDay; }
    public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}