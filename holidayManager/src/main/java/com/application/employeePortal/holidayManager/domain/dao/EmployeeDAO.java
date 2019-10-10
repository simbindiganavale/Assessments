package com.application.employeePortal.holidayManager.domain.dao;

import java.util.List;
import lombok.Data;

@Data
public class EmployeeDAO {

    private long employeeNumber;
    private String firstName;
    private String lastName;
    private List<LeaveDAO>leaves;
}