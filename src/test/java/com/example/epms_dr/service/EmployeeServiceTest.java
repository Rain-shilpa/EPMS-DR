package com.example.epms_dr.service;

import static org.junit.jupiter.api.Assertions.*;
import com.example.epms_dr.model.Employee;
import com.example.epms_dr.model.Department;
import com.example.epms_dr.model.Project;
import com.example.epms_dr.model.PerformanceReview;
import com.example.epms_dr.model.output.EmployeeDetailsResponse;
import com.example.epms_dr.repository.EmployeeRepository;
import com.example.epms_dr.repository.PerformanceReviewRepository;
import com.example.epms_dr.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private PerformanceReviewRepository performanceReviewRepository;

    @Mock
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGetEmployeeDetails() {

        Long employeeId = 1L;

        Department department = new Department(1L, "Engineering", 100000.0);

        Employee employee = Mockito.mock(Employee.class);
        when(employee.getId()).thenReturn(employeeId);
        when(employee.getName()).thenReturn("Alice");
        when(employee.getDepartment()).thenReturn(department);

        Project project = new Project();
        project.setId(1L);
        project.setName("Project Alpha");
        project.setStartDate(LocalDate.of(2023, 1, 1));
        project.setEndDate(LocalDate.of(2023, 12, 31));
        project.setDepartment(department);

        List<Project> projects = List.of(project);

        PerformanceReview review = new PerformanceReview();
        review.setId(1L);
        review.setEmployee(employee);
        review.setReviewDate(LocalDate.of(2023, 6, 1));
        review.setScore(4.5);
        review.setReviewComments("Great performance");

        List<PerformanceReview> reviews = List.of(review);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(projectRepository.findByEmployeeId(employeeId)).thenReturn(projects);
        when(performanceReviewRepository.findTop3ByEmployeeIdOrderByReviewDateDesc(employeeId)).thenReturn(reviews);

        EmployeeDetailsResponse result = employeeService.getEmployeeDetails(employeeId);

        assertNotNull(result);
        assertEquals("Alice", result.getEmployeeName());
        assertEquals("Engineering", result.getDepartmentName());
        assertEquals(1, result.getProjects().size());
        assertEquals("Project Alpha", result.getProjects().get(0).getName());
    }

    @Test
    public void testGetEmployeeDetails_NotFound() {
        Long employeeId = 2L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        EmployeeDetailsResponse result = employeeService.getEmployeeDetails(employeeId);
        assertNull(result);
    }
}