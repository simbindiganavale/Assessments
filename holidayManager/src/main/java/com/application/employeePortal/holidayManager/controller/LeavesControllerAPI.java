package com.application.employeePortal.holidayManager.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.exception.RestAPIException;
import com.application.employeePortal.holidayManager.service.LeavesManagerAsyncService;
import com.application.employeePortal.holidayManager.service.LeavesManagerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  Logger logger = LoggerFactory.getLogger(LeavesControllerAPI.class);

  @Autowired
  private LeavesManagerService leavesService;

  @Autowired
  private LeavesManagerAsyncService leavesAyncService;

  @GetMapping(path = "/leavesOfEmployee/{employeeNumber}")
  public List<LeaveDAO> getAvailableLeavesOfEmployee(@PathVariable long employeeNumber) {
    return leavesService.getLeavesOfEmployee(employeeNumber);
  }

  @PostMapping(path = "/applyLeave/{employeeNumber}")
  @ResponseStatus(HttpStatus.CREATED)
  public List<LeaveDAO> requestLeave(@PathVariable long employeeNumber, @RequestBody LeaveDAO leave) {
    return leavesService.createLeaveRequest(employeeNumber, leave);
  }

  @DeleteMapping(path = "/{employeeNumber}/{year}/{leaveDate}")
  public void removeLeave(@PathVariable long employeeNumber, @PathVariable int year, @PathVariable String leaveDate) {
    leavesService.removeLeave(employeeNumber, year, leaveDate);
  }

  @PostMapping(path = "/allocate/{employeeNumber}")
  @ResponseStatus(HttpStatus.CREATED)
  public List<LeaveDAO> allocateLeaves(@PathVariable long employeeNumber, @RequestBody LeaveDAO leave) {
    return leavesService.createOrUpdateLeaveQuota(employeeNumber, leave);
  }

  @PutMapping(path = "/updateAllocation/{employeeNumber}")
  public List<LeaveDAO> updateAllocatedLeaves(@PathVariable long employeeNumber, @RequestBody LeaveDAO leave) {
    return leavesService.createOrUpdateLeaveQuota(employeeNumber, leave);
  }

  @PostMapping(path = "/applyLeaveAsnyc/{employeeNumber}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public List<LeaveDAO> applyLeaveAsync(@PathVariable long employeeNumber, @RequestBody LeaveDAO leave) {
    List<LeaveDAO>resultList=transformAndReturn(leavesAyncService.createLeaveRequest(employeeNumber, leave));
    logger.info("THIS IS CALLED AFTER THE EXECUTION OF ABOVE SERVICE IS FINISHED");
    return resultList;
  }

  //Temporary workaround to manage the exceptions. Move this to an other layer or to the service
  private List<LeaveDAO> transformAndReturn(List<CompletableFuture<LeaveDAO>> updatedList) {
    logger.info("THIS IS CALLED BEFORE THE EXECUTION OF ABOVE SERVICE IS FINISHED");
    if(updatedList==null){updatedList=new ArrayList<>();}
    return updatedList.stream().map(obj -> {
      try {
        return obj.get();
      } catch (InterruptedException e) {
          throw new RestAPIException(e);
      } catch (ExecutionException e) {
        throw new RestAPIException(e);
      }
    }).collect(Collectors.toList());

    }

}