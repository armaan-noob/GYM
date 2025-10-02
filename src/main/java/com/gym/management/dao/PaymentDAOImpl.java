package com.gym.management.dao;

import com.gym.management.model.Payment;
import com.gym.management.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public Payment create(Payment payment) throws Exception {
        String sql = "INSERT INTO payments (member_id, amount, payment_date, payment_type, status) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, payment.getMemberId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(4, payment.getPaymentType());
            stmt.setString(5, payment.getStatus());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payment.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating payment failed, no ID obtained.");
                }
            }
            return payment;
        }
    }
    
    @Override
    public Payment findById(int id) throws Exception {
        String sql = "SELECT * FROM payments WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToPayment(rs);
            }
            return null;
        }
    }
    
    @Override
    public List<Payment> findByMemberId(int memberId) throws Exception {
        String sql = "SELECT * FROM payments WHERE member_id = ? ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
        }
        return payments;
    }
    
    @Override
    public List<Payment> findByDateRange(LocalDate startDate, LocalDate endDate) throws Exception {
        String sql = "SELECT * FROM payments WHERE payment_date BETWEEN ? AND ? ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
        }
        return payments;
    }
    
    @Override
    public void update(Payment payment) throws Exception {
        String sql = "UPDATE payments SET amount = ?, payment_date = ?, payment_type = ?, status = ? " +
                    "WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, payment.getAmount());
            stmt.setDate(2, Date.valueOf(payment.getPaymentDate()));
            stmt.setString(3, payment.getPaymentType());
            stmt.setString(4, payment.getStatus());
            stmt.setInt(5, payment.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM payments WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setMemberId(rs.getInt("member_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setPaymentDate(rs.getDate("payment_date").toLocalDate());
        payment.setPaymentType(rs.getString("payment_type"));
        payment.setStatus(rs.getString("status"));
        return payment;
    }
}