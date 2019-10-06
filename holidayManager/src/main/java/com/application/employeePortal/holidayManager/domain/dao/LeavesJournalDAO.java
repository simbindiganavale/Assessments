package com.application.employeePortal.holidayManager.domain.dao;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeavesJournalDAO {
    private int numberOfLeavesApplied;
    private LocalDate dateOfRequest;
}