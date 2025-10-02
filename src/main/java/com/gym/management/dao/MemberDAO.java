package com.gym.management.dao;

import com.gym.management.model.Member;
import java.util.List;

public interface MemberDAO {
    Member create(Member member) throws Exception;
    Member findById(int id) throws Exception;
    Member findByEmail(String email) throws Exception;
    List<Member> findAll() throws Exception;
    void update(Member member) throws Exception;
    void delete(int id) throws Exception;
    boolean authenticate(String email, String password) throws Exception;
}