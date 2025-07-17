package com.example.epms_dr.controller;

import com.example.epms_dr.exceptions.ResourceNotFoundException;
import com.example.epms_dr.model.Employee;
import com.example.epms_dr.model.input.EmployeeListRequest;
import com.example.epms_dr.model.output.EmployeeDetailsResponse;
import com.example.epms_dr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/filter")
    public List<Employee> filterEmployees(@RequestBody EmployeeListRequest filter) {
        return employeeService.getFilteredEmployees(filter);
    }

    @GetMapping("/employees/{id}/details")
    public ResponseEntity<EmployeeDetailsResponse> getEmployeeDetails(@PathVariable Long id) {
        EmployeeDetailsResponse employeeDetails = employeeService.getEmployeeDetails(id);
        if (employeeDetails == null) {
            throw  new ResourceNotFoundException("Employee","id",id);
        }
        return ResponseEntity.ok(employeeDetails);
    }
}
