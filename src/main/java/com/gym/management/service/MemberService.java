package com.gym.management.service;

import com.gym.management.model.Member;
import java.util.List;

public interface MemberService {
    Member registerMember(Member member) throws Exception;
    Member getMemberById(int id) throws Exception;
    Member getMemberByEmail(String email) throws Exception;
    List<Member> getAllMembers() throws Exception;
    void updateMember(Member member) throws Exception;
    void deleteMember(int id) throws Exception;
    boolean login(String email, String password) throws Exception;
    boolean isAdmin(String email) throws Exception;
}