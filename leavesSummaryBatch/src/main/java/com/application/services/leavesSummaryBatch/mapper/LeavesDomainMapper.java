package com.application.services.leavesSummaryBatch.mapper;

import java.util.List;

import com.application.services.leavesSummaryBatch.domain.dao.EmployeeDAO;
import com.application.services.leavesSummaryBatch.domain.dao.LeaveDAO;
import com.application.services.leavesSummaryBatch.domain.entities.Employee;
import com.application.services.leavesSummaryBatch.domain.entities.Leave;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeavesDomainMapper{

    EmployeeDAO employeeToDao(Employee employee);  
    LeaveDAO leavesToLeaveDAO(Leave leave);
    List<LeaveDAO> toListOfLeaveDAO(List<Leave>leavesList);    
}