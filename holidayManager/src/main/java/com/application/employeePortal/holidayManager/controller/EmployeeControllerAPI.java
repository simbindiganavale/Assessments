package com.application.employeePortal.holidayManager.controller;



import com.application.employeePortal.holidayManager.domain.dao.EmployeeDAO;
import com.application.employeePortal.holidayManager.service.EmployeesManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeControllerAPI {
    
    @Autowired
    private EmployeesManagerService employeeService;

    @GetMapping(path = "leavesOfEmployee/{employeeNumber}")
    public EmployeeDAO getEmployeeByNumber(@PathVariable long employeeNumber){
      return employeeService.getEmployeeByEmployeeNumber(employeeNumber);
    }

}