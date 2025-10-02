package com.gym.management.model;

import java.time.LocalDate;

public class Payment {
    private int id;
    private int memberId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentType;
    private String status;
    
    // Constructors
    public Payment() {}
    
    public Payment(int memberId, double amount, String paymentType) {
        this.memberId = memberId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentDate = LocalDate.now();
        this.status = "PAID";
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}