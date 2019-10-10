package com.application.employeePortal.holidayManager.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;

public interface LeavesManagerAsyncService {
    public List<CompletableFuture<LeaveDAO>> createLeaveRequest(long employeeNumber, LeaveDAO leave);
}