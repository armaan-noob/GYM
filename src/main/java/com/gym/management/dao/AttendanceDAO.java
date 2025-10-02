package com.gym.management.dao;

import com.gym.management.model.Attendance;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceDAO {
    Attendance create(Attendance attendance) throws Exception;
    Attendance findById(int id) throws Exception;
    List<Attendance> findByMemberId(int memberId) throws Exception;
    List<Attendance> findByDate(LocalDate date) throws Exception;
    void update(Attendance attendance) throws Exception;
    void delete(int id) throws Exception;
    boolean hasCheckedIn(int memberId, LocalDate date) throws Exception;
}