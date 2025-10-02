package com.gym.management.service;

import com.gym.management.dao.PaymentDAO;
import com.gym.management.dao.PaymentDAOImpl;
import com.gym.management.model.Payment;

import java.time.LocalDate;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentDAO paymentDAO;
    
    public PaymentServiceImpl() {
        this.paymentDAO = new PaymentDAOImpl();
    }
    
    @Override
    public Payment recordPayment(Payment payment) throws Exception {
        // Validate payment data
        validatePayment(payment);
        return paymentDAO.create(payment);
    }
    
    @Override
    public Payment getPaymentById(int id) throws Exception {
        Payment payment = paymentDAO.findById(id);
        if (payment == null) {
            throw new Exception("Payment not found");
        }
        return payment;
    }
    
    @Override
    public List<Payment> getMemberPayments(int memberId) throws Exception {
        return paymentDAO.findByMemberId(memberId);
    }
    
    @Override
    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) throws Exception {
        if (startDate.isAfter(endDate)) {
            throw new Exception("Start date must be before end date");
        }
        return paymentDAO.findByDateRange(startDate, endDate);
    }
    
    @Override
    public void updatePayment(Payment payment) throws Exception {
        validatePayment(payment);
        
        if (paymentDAO.findById(payment.getId()) == null) {
            throw new Exception("Payment not found");
        }
        
        paymentDAO.update(payment);
    }
    
    @Override
    public void deletePayment(int id) throws Exception {
        if (paymentDAO.findById(id) == null) {
            throw new Exception("Payment not found");
        }
        paymentDAO.delete(id);
    }
    
    @Override
    public double calculateTotalPayments(int memberId) throws Exception {
        List<Payment> payments = paymentDAO.findByMemberId(memberId);
        return payments.stream()
                .filter(p -> "PAID".equals(p.getStatus()))
                .mapToDouble(Payment::getAmount)
                .sum();
    }
    
    private void validatePayment(Payment payment) throws Exception {
        if (payment.getMemberId() <= 0) {
            throw new Exception("Invalid member ID");
        }
        if (payment.getAmount() <= 0) {
            throw new Exception("Payment amount must be greater than 0");
        }
        if (payment.getPaymentType() == null || payment.getPaymentType().trim().isEmpty()) {
            throw new Exception("Payment type is required");
        }
        if (payment.getPaymentDate() == null) {
            throw new Exception("Payment date is required");
        }
        if (payment.getPaymentDate().isAfter(LocalDate.now())) {
            throw new Exception("Payment date cannot be in the future");
        }
    }
}