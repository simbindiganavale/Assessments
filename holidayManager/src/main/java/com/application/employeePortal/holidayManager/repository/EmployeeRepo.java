package com.application.employeePortal.holidayManager.repository;

import com.application.employeePortal.holidayManager.domain.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByEmployeeNumber(long employeeNumber);
}