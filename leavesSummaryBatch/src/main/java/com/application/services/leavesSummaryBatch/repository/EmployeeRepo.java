package com.application.services.leavesSummaryBatch.repository;


import com.application.services.leavesSummaryBatch.domain.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByEmployeeNumber(long employeeNumber);
}