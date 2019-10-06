package com.application.employeePortal.holidayManager.service;

import java.util.List;

import com.application.employeePortal.holidayManager.Utils.GlobalUtils;
import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.factory.LeavesManagerFactory;
import com.application.employeePortal.holidayManager.mapper.LeavesMapper;
import com.application.employeePortal.holidayManager.repository.LeavesRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeavesManagerServiceImpl implements LeavesManagerService {

    @Autowired
    private LeavesRepo leavesRepo;

    @Autowired
    private LeavesManagerFactory leavesFactory;

    @Autowired
    private LeavesMapper mapper;

    public List<LeaveDAO> getLeavesOfEmployee(long employeeNumber){
        return mapper.toListOfLeaveDAO(leavesRepo.findLeavesByEmployeeNumber(employeeNumber));
    }

    public void addLeave(long employeeNumber,int year, String leaveDate){
        leavesFactory.updateLeave(employeeNumber, year,  GlobalUtils.parseToLocalDate(leaveDate, GlobalUtils.DATEFORMAT));
    }

    public void removeLeave(long employeeNumber,int year, String leaveDate){
        leavesFactory.deleteLeave(employeeNumber, year,  GlobalUtils.parseToLocalDate(leaveDate, GlobalUtils.DATEFORMAT));
    }
}