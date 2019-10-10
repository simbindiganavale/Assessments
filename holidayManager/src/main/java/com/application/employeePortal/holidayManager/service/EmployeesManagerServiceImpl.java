package com.application.employeePortal.holidayManager.service;


import com.application.employeePortal.holidayManager.domain.dao.EmployeeDAO;
import com.application.employeePortal.holidayManager.factory.LeavesManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesManagerServiceImpl implements EmployeesManagerService {

    @Autowired
    private LeavesManagerFactory factory;

    public EmployeeDAO getEmployeeByEmployeeNumber(long employeeNumber){
        return factory.getEmployeeByNumber(employeeNumber);
    }
}

