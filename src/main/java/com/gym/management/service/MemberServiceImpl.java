package com.gym.management.service;

import com.gym.management.dao.MemberDAO;
import com.gym.management.dao.MemberDAOImpl;
import com.gym.management.model.Member;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    
    public MemberServiceImpl() {
        this.memberDAO = new MemberDAOImpl();
    }
    
    @Override
    public Member registerMember(Member member) throws Exception {
        // Validate member data
        validateMember(member);
        
        // Check if email already exists
        if (memberDAO.findByEmail(member.getEmail()) != null) {
            throw new Exception("Email already registered");
        }
        
        return memberDAO.create(member);
    }
    
    @Override
    public Member getMemberById(int id) throws Exception {
        Member member = memberDAO.findById(id);
        if (member == null) {
            throw new Exception("Member not found");
        }
        return member;
    }
    
    @Override
    public Member getMemberByEmail(String email) throws Exception {
        Member member = memberDAO.findByEmail(email);
        if (member == null) {
            throw new Exception("Member not found");
        }
        return member;
    }
    
    @Override
    public List<Member> getAllMembers() throws Exception {
        return memberDAO.findAll();
    }
    
    @Override
    public void updateMember(Member member) throws Exception {
        validateMember(member);
        
        // Check if member exists
        if (memberDAO.findById(member.getId()) == null) {
            throw new Exception("Member not found");
        }
        
        memberDAO.update(member);
    }
    
    @Override
    public void deleteMember(int id) throws Exception {
        if (memberDAO.findById(id) == null) {
            throw new Exception("Member not found");
        }
        memberDAO.delete(id);
    }
    
    @Override
    public boolean login(String email, String password) throws Exception {
        return memberDAO.authenticate(email, password);
    }
    
    @Override
    public boolean isAdmin(String email) throws Exception {
        Member member = memberDAO.findByEmail(email);
        return member != null && "ADMIN".equals(member.getUserRole());
    }
    
    private void validateMember(Member member) throws Exception {
        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new Exception("Name is required");
        }
        if (member.getAge() < 16 || member.getAge() > 100) {
            throw new Exception("Age must be between 16 and 100");
        }
        if (member.getContact() == null || !member.getContact().matches("\\d{10}")) {
            throw new Exception("Invalid contact number");
        }
        if (member.getEmail() == null || !member.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new Exception("Invalid email address");
        }
        if (member.getPassword() == null || member.getPassword().length() < 6) {
            throw new Exception("Password must be at least 6 characters");
        }
    }
}