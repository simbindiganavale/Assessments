package com.application.employeePortal.holidayManager.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LEAVE")
public class Leave {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "AVAILABLE_LEAVES")
    private int numberOfLeaves;
    @Column(name = "ALLOCATED_LEAVES")
    private int allocatedLeaves;
    @Column(name = "YEAR")
    private int year;
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leaveAccount")
    private List<LeaveJournal> leavesTaken;

}