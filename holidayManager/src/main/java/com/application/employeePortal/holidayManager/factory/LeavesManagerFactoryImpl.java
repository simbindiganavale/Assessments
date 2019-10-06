package com.application.employeePortal.holidayManager.factory;

import java.time.LocalDate;

import com.application.employeePortal.holidayManager.domain.entities.Leave;
import com.application.employeePortal.holidayManager.repository.LeavesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

@Component
public class LeavesManagerFactoryImpl implements LeavesManagerFactory {

    @Autowired
    private LeavesRepo repo;

   // @Transactional(propagation = Propagation.REQUIRED)
    public void updateLeave(long employeeNumber,int year, LocalDate leaveDate){
        Leave leaveObj=repo.findLeavesByEmployeeAndYear(employeeNumber,year);
        //TODO: throw an exception if object is not found
        leaveObj.addEntryToJournal(leaveDate);
        leaveObj.setLeaveBalance(leaveObj.getAllocatedLeaves()-1);
        repo.save(leaveObj);
    }

    public void deleteLeave(long employeeNumber,int year, LocalDate leaveDate){
        Leave leaveObj=repo.findLeavesByEmployeeAndYear(employeeNumber,year);
        //TODO: throw an exception if object is not found
        leaveObj.removeEntryFromJournal(leaveDate);
        leaveObj.setLeaveBalance(leaveObj.getAllocatedLeaves()+1);
        repo.save(leaveObj);
    }

    
    
}