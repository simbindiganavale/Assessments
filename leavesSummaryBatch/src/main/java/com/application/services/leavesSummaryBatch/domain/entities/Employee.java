package com.application.services.leavesSummaryBatch.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="EMPLOYEE")
@NamedQueries({@NamedQuery(name="Employee.findByEmployeeNumber", query="Select e from Employee e where e.employeeNumber=?1")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 9212385747055970220L;
    
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name="EMPLOYEE_NUMBER")
    private long employeeNumber;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leave>leaves;

    public void allocateLeaves(int allocatedQuota, int year){
        Leave leaveObjToPersist=leaves.stream().filter(obj -> obj.getYear()==year).findAny()
        .orElse(new Leave(this));
        leaveObjToPersist.setAllocatedLeaves(allocatedQuota);
        leaveObjToPersist.setLeaveBalance(allocatedQuota);
       // leaveObjToPersist.setId(Optional.ofNullable(leaveObjToPersist.getId()).orElse(new LeaveIdentity())); 
        leaveObjToPersist.setYear(year);
    }
}