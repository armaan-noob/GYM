package com.gym.management.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private int id;
    private int memberId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    
    // Constructors
    public Attendance() {}
    
    public Attendance(int memberId) {
        this.memberId = memberId;
        this.date = LocalDate.now();
        this.checkInTime = LocalTime.now();
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public LocalTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalTime checkInTime) { this.checkInTime = checkInTime; }
    
    public LocalTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalTime checkOutTime) { this.checkOutTime = checkOutTime; }
}