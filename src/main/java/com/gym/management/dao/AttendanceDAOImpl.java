package com.gym.management.dao;

import com.gym.management.model.Attendance;
import com.gym.management.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public Attendance create(Attendance attendance) throws Exception {
        String sql = "INSERT INTO attendance (member_id, date, check_in_time, check_out_time) " +
                    "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, attendance.getMemberId());
            stmt.setDate(2, Date.valueOf(attendance.getDate()));
            stmt.setTime(3, Time.valueOf(attendance.getCheckInTime()));
            stmt.setTime(4, attendance.getCheckOutTime() != null ? 
                        Time.valueOf(attendance.getCheckOutTime()) : null);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating attendance failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    attendance.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating attendance failed, no ID obtained.");
                }
            }
            return attendance;
        }
    }
    
    @Override
    public Attendance findById(int id) throws Exception {
        String sql = "SELECT * FROM attendance WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToAttendance(rs);
            }
            return null;
        }
    }
    
    @Override
    public List<Attendance> findByMemberId(int memberId) throws Exception {
        String sql = "SELECT * FROM attendance WHERE member_id = ? ORDER BY date DESC";
        List<Attendance> attendances = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }
        }
        return attendances;
    }
    
    @Override
    public List<Attendance> findByDate(LocalDate date) throws Exception {
        String sql = "SELECT * FROM attendance WHERE date = ?";
        List<Attendance> attendances = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                attendances.add(mapResultSetToAttendance(rs));
            }
        }
        return attendances;
    }
    
    @Override
    public void update(Attendance attendance) throws Exception {
        String sql = "UPDATE attendance SET check_out_time = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTime(1, Time.valueOf(attendance.getCheckOutTime()));
            stmt.setInt(2, attendance.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM attendance WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public boolean hasCheckedIn(int memberId, LocalDate date) throws Exception {
        String sql = "SELECT COUNT(*) FROM attendance WHERE member_id = ? AND date = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, memberId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
    
    private Attendance mapResultSetToAttendance(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(rs.getInt("id"));
        attendance.setMemberId(rs.getInt("member_id"));
        attendance.setDate(rs.getDate("date").toLocalDate());
        attendance.setCheckInTime(rs.getTime("check_in_time").toLocalTime());
        Time checkOutTime = rs.getTime("check_out_time");
        if (checkOutTime != null) {
            attendance.setCheckOutTime(checkOutTime.toLocalTime());
        }
        return attendance;
    }
}