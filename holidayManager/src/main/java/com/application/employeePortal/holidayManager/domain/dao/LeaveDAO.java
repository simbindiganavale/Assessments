package com.application.employeePortal.holidayManager.domain.dao;

import java.util.List;
import lombok.Data;

@Data
public class LeaveDAO {
    private int numberOfLeaves;
    private int allocatedLeaves;
    private int year;
    private long employeeNumber;
    private List<LeavesJournalDAO> leavesTaken;

}