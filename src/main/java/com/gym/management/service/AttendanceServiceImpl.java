package com.gym.management.service;

import com.gym.management.dao.AttendanceDAO;
import com.gym.management.dao.AttendanceDAOImpl;
import com.gym.management.model.Attendance;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDAO attendanceDAO;
    
    public AttendanceServiceImpl() {
        this.attendanceDAO = new AttendanceDAOImpl();
    }
    
    @Override
    public Attendance recordCheckIn(int memberId) throws Exception {
        if (hasCheckedIn(memberId)) {
            throw new Exception("Member has already checked in today");
        }
        
        Attendance attendance = new Attendance(memberId);
        return attendanceDAO.create(attendance);
    }
    
    @Override
    public Attendance recordCheckOut(int memberId) throws Exception {
        LocalDate today = LocalDate.now();
        List<Attendance> attendances = attendanceDAO.findByDate(today);
        
        Attendance attendance = attendances.stream()
                .filter(a -> a.getMemberId() == memberId && a.getCheckOutTime() == null)
                .findFirst()
                .orElseThrow(() -> new Exception("No active check-in found for member"));
        
        attendance.setCheckOutTime(java.time.LocalTime.now());
        attendanceDAO.update(attendance);
        return attendance;
    }
    
    @Override
    public List<Attendance> getMemberAttendance(int memberId) throws Exception {
        return attendanceDAO.findByMemberId(memberId);
    }
    
    @Override
    public List<Attendance> getDateAttendance(LocalDate date) throws Exception {
        return attendanceDAO.findByDate(date);
    }
    
    @Override
    public boolean hasCheckedIn(int memberId) throws Exception {
        return attendanceDAO.hasCheckedIn(memberId, LocalDate.now());
    }
    
    @Override
    public double calculateAttendanceRate(int memberId, LocalDate startDate, LocalDate endDate) throws Exception {
        if (startDate.isAfter(endDate)) {
            throw new Exception("Start date must be before end date");
        }
        
        List<Attendance> attendances = attendanceDAO.findByMemberId(memberId);
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        
        long daysAttended = attendances.stream()
                .filter(a -> !a.getDate().isBefore(startDate) && !a.getDate().isAfter(endDate))
                .count();
        
        return (double) daysAttended / totalDays * 100;
    }
}