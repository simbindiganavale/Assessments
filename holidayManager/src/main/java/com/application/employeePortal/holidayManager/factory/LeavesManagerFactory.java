package com.application.employeePortal.holidayManager.factory;

import java.time.LocalDate;
import java.util.List;

import com.application.employeePortal.holidayManager.domain.dao.EmployeeDAO;
import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;

public interface LeavesManagerFactory{

    public List<LeaveDAO> createLeave(long employeeNumber,int year, LocalDate leaveDate);
    public void deleteLeave(long employeeNumber,int year, LocalDate leaveDate);
    public List<LeaveDAO> getLeavesOfEmployee(long employeeNumber);
    public List<LeaveDAO> createOrUpdateLeaveQuota(long employeeNumber,int year, int allocatedQuota);
    public EmployeeDAO getEmployeeByNumber(long employeeNumber);
    public List<LeaveDAO> getLeavesOfEmployeeTransactional(long employeeNumber);
}