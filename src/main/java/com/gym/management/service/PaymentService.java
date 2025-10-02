package com.gym.management.service;

import com.gym.management.model.Payment;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    Payment recordPayment(Payment payment) throws Exception;
    Payment getPaymentById(int id) throws Exception;
    List<Payment> getMemberPayments(int memberId) throws Exception;
    List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) throws Exception;
    void updatePayment(Payment payment) throws Exception;
    void deletePayment(int id) throws Exception;
    double calculateTotalPayments(int memberId) throws Exception;
}