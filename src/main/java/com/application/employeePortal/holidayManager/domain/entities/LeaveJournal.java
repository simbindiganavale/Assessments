package com.application.employeePortal.holidayManager.domain.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LEAVE_JOURNAL")
public class LeaveJournal {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "LEAVE_DAYS")
    private int numberOfLeavesApplied;
    @Column(name = "YEAR")
    private Date dateOfRequest;
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "KEAVE_ACCOUNT_ID")
    private Leave leaveAccount;
}