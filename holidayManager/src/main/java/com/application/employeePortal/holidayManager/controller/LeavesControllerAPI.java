package com.application.employeePortal.holidayManager.controller;

import java.util.List;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.service.LeavesManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/leaves")
public class LeavesControllerAPI {
    
    @Autowired
    private LeavesManagerService leavesService;

    @GetMapping(path = "/leavesOfEmployee/{employeeNumber}")
    public List<LeaveDAO> getAvailableLeavesOfEmployee(@PathVariable long employeeNumber){
      return leavesService.getLeavesOfEmployee(employeeNumber);
    }

    @PostMapping(path="/applyLeave/{employeeNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<LeaveDAO> requestLeave(@PathVariable long employeeNumber,@RequestBody LeaveDAO leave){
      return leavesService.createLeaveRequest(employeeNumber, leave);
    }


    @DeleteMapping(path="/{employeeNumber}/{year}/{leaveDate}")
    public void removeLeave(@PathVariable long employeeNumber,@PathVariable int year,@PathVariable String leaveDate){
      leavesService.removeLeave(employeeNumber, year, leaveDate);
    }

    @PostMapping(path="/allocate/{employeeNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<LeaveDAO> allocateLeaves(@PathVariable long employeeNumber,@RequestBody LeaveDAO leave){
      return leavesService.createOrUpdateLeaveQuota(employeeNumber, leave);
    }

    @PutMapping(path="/updateAllocation/{employeeNumber}")
    public List<LeaveDAO> updateAllocatedLeaves(@PathVariable long employeeNumber,@RequestBody LeaveDAO leave){
      return leavesService.createOrUpdateLeaveQuota(employeeNumber, leave);
    }

}