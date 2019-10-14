package com.application.services.leavesSummaryBatch.domain.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="LEAVE_JOURNAL")
public class LeaveJournal implements Serializable{
 
    private static final long serialVersionUID = -2038533686114732499L;
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "LEAVE_DATE")
    private LocalDate leaveDate;
    @Column(name = "REQUEST_DATE")
    private LocalDate dateOfRequest;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(
            name = "LEAVE_ACCOUNT_ID",
            referencedColumnName = "ID"),
        @JoinColumn(
            name = "LEAVE_YEAR",
            referencedColumnName = "YEAR")
    })
    private Leave leaveAccount;

    public LeaveJournal(LocalDate leaveDate2){
        this.leaveDate=leaveDate2;
    }

    public LeaveJournal(){
        super();
    }
}