package com.example.epms_dr.service;

import com.example.epms_dr.model.Department;
import com.example.epms_dr.model.Employee;
import com.example.epms_dr.model.PerformanceReview;
import com.example.epms_dr.model.Project;
import com.example.epms_dr.model.input.EmployeeListRequest;
import com.example.epms_dr.model.output.EmployeeDetailsResponse;
import com.example.epms_dr.repository.EmployeeRepository;
import com.example.epms_dr.repository.PerformanceReviewRepository;
import com.example.epms_dr.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository  employeeRepository;

    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Employee> getFilteredEmployees(EmployeeListRequest filter) {
        return employeeRepository.findFilteredEmployees(filter);
    }

    public EmployeeDetailsResponse getEmployeeDetails(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return null;
        }

        Department department = employee.getDepartment();

        List<Project> projects = projectRepository.findByEmployeeId(id);

        List<PerformanceReview> performanceReviews = performanceReviewRepository
                .findTop3ByEmployeeIdOrderByReviewDateDesc(id);

        return new EmployeeDetailsResponse(employee.getId(), employee.getName(),department.getName(), projects, performanceReviews);
    }
}
