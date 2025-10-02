package com.gym.management.dao;

import com.gym.management.model.Payment;
import java.time.LocalDate;
import java.util.List;

public interface PaymentDAO {
    Payment create(Payment payment) throws Exception;
    Payment findById(int id) throws Exception;
    List<Payment> findByMemberId(int memberId) throws Exception;
    List<Payment> findByDateRange(LocalDate startDate, LocalDate endDate) throws Exception;
    void update(Payment payment) throws Exception;
    void delete(int id) throws Exception;
}