package com.application.employeePortal.holidayManager.business;

import java.util.NoSuchElementException;

import com.application.employeePortal.holidayManager.domain.entities.Employee;
import com.application.employeePortal.holidayManager.domain.entities.Leave;
import com.application.employeePortal.holidayManager.exception.EmployeeNotFoundException;
import com.application.employeePortal.holidayManager.exception.LeavesAllocationException;

import org.springframework.stereotype.Component;

@Component
public class Validator{

    public void validateEmployee(Employee employeeRetreived){
        if (null==employeeRetreived){
           throw new EmployeeNotFoundException("Employee not found in the repository");
        }
    }

    public void validateLeaveAllocation(Employee employeeRetreived, int year){
        if(employeeRetreived.getLeaves().isEmpty()){
            throw new LeavesAllocationException("Leaves are not yet allocated for the employee "+ employeeRetreived.getEmployeeNumber());
        }
        try{
            employeeRetreived.getLeaves().stream().filter(obj -> obj.getYear()==year).findFirst().get();
        }catch (NoSuchElementException n){
            throw new LeavesAllocationException("Leaves are not yet allocated for the employee "+ 
            employeeRetreived.getEmployeeNumber() + " in the year " + year);
        }
    }

    public void validateLeaveEligibility(Employee employeeRetreived, int year){
        try{
            Leave leave=employeeRetreived.getLeaves().stream().filter(obj -> obj.getYear()==year).findFirst().get();
            if(leave.getLeaveBalance()==0){
                throw new LeavesAllocationException("Leave Quota exhausted for the year "+ year);
            }
        }catch (NoSuchElementException n){
            throw new LeavesAllocationException("Leaves are not yet allocated for the employee "+ employeeRetreived.getEmployeeNumber());
        }
      
    }
}