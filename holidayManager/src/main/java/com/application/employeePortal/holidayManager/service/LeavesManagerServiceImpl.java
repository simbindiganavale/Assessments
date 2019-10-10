package com.application.employeePortal.holidayManager.service;

import java.util.List;
import com.application.employeePortal.holidayManager.Utils.GlobalUtils;
import com.application.employeePortal.holidayManager.domain.dao.LeaveDAO;
import com.application.employeePortal.holidayManager.factory.LeavesManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeavesManagerServiceImpl implements LeavesManagerService {

    @Autowired
    private LeavesManagerFactory leavesFactory;


    public List<LeaveDAO> getLeavesOfEmployee(long employeeNumber){
        return leavesFactory.getLeavesOfEmployee(employeeNumber);
    }

    public List<LeaveDAO> createLeaveRequest(long employeeNumber,LeaveDAO leave){
        leave.getLeavesTaken().forEach(obj -> {
            leavesFactory.createLeave(employeeNumber, leave.getYear(),  
                    GlobalUtils.parseToLocalDate(obj.getLeaveDate(), GlobalUtils.DATEFORMAT));
        });
        return getLeavesOfEmployee(employeeNumber);
    }

    public void removeLeave(long employeeNumber,int year, String leaveDate){
        leavesFactory.deleteLeave(employeeNumber, year,  GlobalUtils.parseToLocalDate(leaveDate, GlobalUtils.DATEFORMAT));
    }

    //Added transactional here only to illustrated that this transaction will eventually be suspended by the subsequent inner transaction
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<LeaveDAO> createOrUpdateLeaveQuota(long employeeNumber,LeaveDAO leave){
        return leavesFactory.createOrUpdateLeaveQuota(employeeNumber, leave.getYear(), leave.getAllocatedLeaves()); 
    }

}