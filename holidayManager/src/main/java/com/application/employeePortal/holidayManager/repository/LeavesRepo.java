package com.application.employeePortal.holidayManager.repository;

import java.util.List;

import com.application.employeePortal.holidayManager.domain.entities.Leave;
import com.application.employeePortal.holidayManager.domain.entities.LeaveIdentity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeavesRepo extends JpaRepository<Leave, LeaveIdentity> {
    List<Leave> findLeavesByEmployeeNumber(long employeeNumber);
    Leave findLeavesByEmployeeAndYear(long employeeNumber, int year);
}