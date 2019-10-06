package com.application.employeePortal.holidayManager.factory;

import java.time.LocalDate;

public interface LeavesManagerFactory{

    public void updateLeave(long employeeNumber,int year, LocalDate leaveDate);
    public void deleteLeave(long employeeNumber,int year, LocalDate leaveDate);
}