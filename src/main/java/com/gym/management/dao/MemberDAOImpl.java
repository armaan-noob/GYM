package com.gym.management.dao;

import com.gym.management.model.Member;
import com.gym.management.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public Member create(Member member) throws Exception {
        String sql = "INSERT INTO members (name, age, contact, email, membership_type, join_date, user_role, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getContact());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getMembershipType());
            stmt.setDate(6, Date.valueOf(member.getJoinDate()));
            stmt.setString(7, member.getUserRole());
            stmt.setString(8, member.getPassword());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating member failed, no rows affected.");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    member.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating member failed, no ID obtained.");
                }
            }
            return member;
        }
    }
    
    @Override
    public Member findById(int id) throws Exception {
        String sql = "SELECT * FROM members WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToMember(rs);
            }
            return null;
        }
    }
    
    @Override
    public Member findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM members WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToMember(rs);
            }
            return null;
        }
    }
    
    @Override
    public List<Member> findAll() throws Exception {
        String sql = "SELECT * FROM members ORDER BY name";
        List<Member> members = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                members.add(mapResultSetToMember(rs));
            }
        }
        return members;
    }
    
    @Override
    public void update(Member member) throws Exception {
        String sql = "UPDATE members SET name = ?, age = ?, contact = ?, email = ?, " +
                    "membership_type = ?, user_role = ?, password = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, member.getName());
            stmt.setInt(2, member.getAge());
            stmt.setString(3, member.getContact());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getMembershipType());
            stmt.setString(6, member.getUserRole());
            stmt.setString(7, member.getPassword());
            stmt.setInt(8, member.getId());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM members WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public boolean authenticate(String email, String password) throws Exception {
        String sql = "SELECT COUNT(*) FROM members WHERE email = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
    
    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        Member member = new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        member.setAge(rs.getInt("age"));
        member.setContact(rs.getString("contact"));
        member.setEmail(rs.getString("email"));
        member.setMembershipType(rs.getString("membership_type"));
        member.setJoinDate(rs.getDate("join_date").toLocalDate());
        member.setUserRole(rs.getString("user_role"));
        member.setPassword(rs.getString("password"));
        return member;
    }
}