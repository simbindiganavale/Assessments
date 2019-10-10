package com.application.employeePortal.holidayManager.factory;

import java.time.LocalDate;
import java.util.List;

import com.application.employeePortal.holidayManager.business.Validator;
import com.application.employeePortal.holidayManager.domain.dao.EmployeeDAO;
import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.domain.entities.Employee;
import com.application.employeePortal.holidayManager.domain.entities.Leave;
import com.application.employeePortal.holidayManager.mapper.LeavesDomainMapper;
import com.application.employeePortal.holidayManager.repository.EmployeeRepo;
import com.application.employeePortal.holidayManager.repository.LeavesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LeavesManagerFactoryImpl implements LeavesManagerFactory {

    @Autowired
    private LeavesRepo leavesRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeavesDomainMapper mapper;
     
    @Autowired
    private Validator validator;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<LeaveDAO> createLeave(long employeeNumber,int year, LocalDate leaveDate){
        Employee retreivedEmployee=employeeRepo.findByEmployeeNumber(employeeNumber);
        validateLeavesBeforeUpdate(retreivedEmployee, year);
        Leave leaveObj=leavesRepo.findLeavesByEmployeeAndYear(employeeNumber,year);
        leaveObj.addEntryToJournal(leaveDate);
        leaveObj.setLeaveBalance(leaveObj.getAllocatedLeaves()-1);
        leavesRepo.save(leaveObj);
        return mapper.toListOfLeaveDAO(retreivedEmployee.getLeaves());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<LeaveDAO> createOrUpdateLeaveQuota(long employeeNumber,int year, int allocatedQuota){
        Employee retreivedEmployee=employeeRepo.findByEmployeeNumber(employeeNumber);
        validator.validateEmployee(retreivedEmployee);
        retreivedEmployee.allocateLeaves(allocatedQuota, year);
        retreivedEmployee=employeeRepo.save(retreivedEmployee);
        return mapper.toListOfLeaveDAO(retreivedEmployee.getLeaves());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteLeave(long employeeNumber,int year, LocalDate leaveDate){
        Employee retreivedEmployee=employeeRepo.findByEmployeeNumber(employeeNumber);
        validateLeavesBeforeDelete(retreivedEmployee, year);
        Leave leaveObj=leavesRepo.findLeavesByEmployeeAndYear(employeeNumber,year);
        leaveObj.removeEntryFromJournal(leaveDate);
        leaveObj.setLeaveBalance(leaveObj.getAllocatedLeaves()+1);
        leavesRepo.save(leaveObj);
    }

    public List<LeaveDAO> getLeavesOfEmployee(long employeeNumber){
        validator.validateEmployee(employeeRepo.findByEmployeeNumber(employeeNumber));
        return mapper.toListOfLeaveDAO(leavesRepo.findLeavesByEmployeeNumber(employeeNumber));
    }

    private void validateLeavesBeforeUpdate(Employee retreivedEmployee,int year){
        validator.validateLeaveAllocation(retreivedEmployee, year);
        validator.validateLeaveEligibility(retreivedEmployee, year);
    }

    private void validateLeavesBeforeDelete(Employee retreivedEmployee,int year){
        validator.validateLeaveAllocation(retreivedEmployee, year);
    }

    public EmployeeDAO getEmployeeByNumber(long employeeNumber){
        Employee retreivedEmployee=employeeRepo.findByEmployeeNumber(employeeNumber);
        validator.validateEmployee(retreivedEmployee);
        return mapper.employeeToDao(retreivedEmployee);
    }



    
}