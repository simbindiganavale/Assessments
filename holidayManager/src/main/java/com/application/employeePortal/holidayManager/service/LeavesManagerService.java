package com.application.employeePortal.holidayManager.service;

import java.util.List;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;

public interface LeavesManagerService {
     List<LeaveDAO> getLeavesOfEmployee(long employeeNumber);
     List<LeaveDAO> createLeaveRequest(long employeeNumber,LeaveDAO leave);
     void removeLeave(long employeeNumber,int year, String leaveDate);
     List<LeaveDAO> createOrUpdateLeaveQuota(long employeeNumber,LeaveDAO leave);
}
