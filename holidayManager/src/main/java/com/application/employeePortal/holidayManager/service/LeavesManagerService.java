package com.application.employeePortal.holidayManager.service;

import java.util.List;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;

public interface LeavesManagerService {
     List<LeaveDAO> getLeavesOfEmployee(long employeeNumber);
     void addLeave(long employeeNumber,int year, String leaveDate);
     void removeLeave(long employeeNumber,int year, String leaveDate);
}
