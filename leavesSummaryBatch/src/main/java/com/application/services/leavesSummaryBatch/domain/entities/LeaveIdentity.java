package com.application.services.leavesSummaryBatch.domain.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class LeaveIdentity implements Serializable{

    private static final long serialVersionUID = -7838422450395029511L;

    private long id;
 
    private int year;
}