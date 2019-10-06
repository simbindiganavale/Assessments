package com.application.employeePortal.holidayManager.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

import lombok.Data;

@Data
@Entity
@Table(name = "LEAVE")
@NamedQueries({
        @NamedQuery(name = "Leave.findLeavesByEmployeeNumber", query = "Select l from Leave l where l.employee.employeeNumber=?1"),
        @NamedQuery(name = "Leave.findLeavesByEmployeeAndYear", query = "Select l from Leave l where l.employee.employeeNumber=?1 and l.id.year=?2") })

public class Leave {

    @EmbeddedId
    private LeaveIdentity id;
    @Column(name = "AVAILABLE_LEAVES")
    private int leaveBalance;
    @Column(name = "ALLOCATED_LEAVES")
    private int allocatedLeaves;
    @ManyToOne(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "leaveAccount", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<LeaveJournal> leavesTaken=new ArrayList<LeaveJournal>();

    public void addEntryToJournal(LocalDate leaveDate) {
        LeaveJournal journalObj = null;
        try{
            journalObj= findByDate(leaveDate).get();
        }catch(NoSuchElementException e){
                //No leaves recorded for the given date
            journalObj = new LeaveJournal(leaveDate);  
        }
        journalObj.setLeaveAccount(this);
        journalObj.setDateOfRequest(LocalDate.now());
        leavesTaken.add(journalObj);
       
    }

    public void removeEntryFromJournal(LocalDate leaveDate) {
        if (!CollectionUtils.isEmpty(leavesTaken)) {
            LeaveJournal journalObj = findByDate(leaveDate).get();
                    //Handle no such element exception
             leavesTaken.remove(journalObj);       
        }else{
            //Throw a data not found exception
        }
    }

    private Optional<LeaveJournal> findByDate(LocalDate leaveDate){
        return leavesTaken.stream().filter(obj -> obj.getLeaveDate().equals(leaveDate))
            .findFirst();
    }
}