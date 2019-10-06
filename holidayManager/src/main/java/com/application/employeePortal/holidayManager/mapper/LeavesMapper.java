package com.application.employeePortal.holidayManager.mapper;

import java.util.List;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.domain.entities.Leave;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeavesMapper{


@Mapping(target = "employeeNumber", source = "employee.employeeNumber")
@Mapping(target = "year", source = "id.year")
LeaveDAO leavesToLeaveDAO(Leave leave);

List<LeaveDAO> toListOfLeaveDAO(List<Leave>leavesList);    
}