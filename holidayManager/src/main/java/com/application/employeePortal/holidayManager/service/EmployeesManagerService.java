package com.application.employeePortal.holidayManager.service;

import com.application.employeePortal.holidayManager.domain.dao.EmployeeDAO;

public interface EmployeesManagerService {
    public EmployeeDAO getEmployeeByEmployeeNumber(long employeeNumber);
}