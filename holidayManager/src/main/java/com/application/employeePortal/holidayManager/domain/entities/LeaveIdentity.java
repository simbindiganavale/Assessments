package com.application.employeePortal.holidayManager.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Embeddable
public class LeaveIdentity implements Serializable{

    private static final long serialVersionUID = -7838422450395029511L;

    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
 
    @Column(name = "YEAR")
    private int year;
}