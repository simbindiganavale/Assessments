package com.application.services.leavesSummaryBatch.domain.dao;

import java.util.List;
import lombok.Data;

@Data
public class LeaveDAO {
    private int leaveBalance;
    private int allocatedLeaves;
    private int year;
    private List<LeavesJournalDAO> leavesTaken;

}