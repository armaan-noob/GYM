package com.gym.management.service;

import com.gym.management.model.Attendance;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Attendance recordCheckIn(int memberId) throws Exception;
    Attendance recordCheckOut(int memberId) throws Exception;
    List<Attendance> getMemberAttendance(int memberId) throws Exception;
    List<Attendance> getDateAttendance(LocalDate date) throws Exception;
    boolean hasCheckedIn(int memberId) throws Exception;
    double calculateAttendanceRate(int memberId, LocalDate startDate, LocalDate endDate) throws Exception;
}