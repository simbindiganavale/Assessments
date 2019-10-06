package com.application.employeePortal.holidayManager.domain.entities;

import java.util.List;

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
public class Employee {
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Leave>leaves;
}