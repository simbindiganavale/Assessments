package com.application.services.leavesSummaryBatch.batch;

import com.application.services.leavesSummaryBatch.domain.dao.EmployeeDAO;
import com.application.services.leavesSummaryBatch.domain.entities.Employee;
import com.application.services.leavesSummaryBatch.mapper.LeavesDomainMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeItemProcessor implements ItemProcessor<Employee, EmployeeDAO> {

    private static final Logger log = LoggerFactory.getLogger(EmployeeItemProcessor.class);

    @Autowired
    LeavesDomainMapper mapper;

    @Override
    public EmployeeDAO process(final Employee employee) throws Exception {
        EmployeeDAO output=mapper.employeeToDao(employee);
        output.setLastName(output.getLastName().toUpperCase());
        log.info("Processed " + output + " from " + employee);
        return new EmployeeDAO();
    }

}