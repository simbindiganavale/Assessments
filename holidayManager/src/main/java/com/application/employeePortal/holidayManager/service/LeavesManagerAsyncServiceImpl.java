package com.application.employeePortal.holidayManager.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.application.employeePortal.holidayManager.Utils.GlobalUtils;
import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.factory.LeavesManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LeavesManagerAsyncServiceImpl implements LeavesManagerAsyncService {

    @Autowired
    private LeavesManagerFactory leavesFactory;


    @Override
    @Async()
    public List<CompletableFuture<LeaveDAO>> createLeaveRequest(long employeeNumber, LeaveDAO leave) {
        leave.getLeavesTaken().forEach(obj -> {
            leavesFactory.createLeave(employeeNumber, leave.getYear(),  
                    GlobalUtils.parseToLocalDate(obj.getLeaveDate(), GlobalUtils.DATEFORMAT));
        });
        return leavesFactory.getLeavesOfEmployeeTransactional(employeeNumber).stream().map(obj -> 
        CompletableFuture.completedFuture(obj)).collect(Collectors.toList());
    }

}