package com.example.epms_dr.controller;

import com.example.epms_dr.model.Employee;
import com.example.epms_dr.model.output.EmployeeDetailsResponse;
import com.example.epms_dr.service.EmployeeService;
import com.example.epms_dr.model.input.EmployeeListRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void testFilterEmployees() throws Exception {

        EmployeeListRequest filter = new EmployeeListRequest();
        filter.setScore(4.5);
        filter.setDepartmentId(List.of(1L, 2L));
        filter.setProjectId(List.of(3L, 4L));
        filter.setReviewDate(LocalDate.of(2023, 6, 1));

        List<Employee> employees = List.of(
                new Employee(1L, "Alice", "alice@example.com", 1, LocalDate.of(2021, 1, 15), 75000, 9),
                new Employee(2L, "Bob", "bob@example.com", 2, LocalDate.of(2020, 3, 10), 80000, 1)
        );

        Mockito.doReturn(employees).when(employeeService).getFilteredEmployees(Mockito.any(EmployeeListRequest.class));

        String json = """
                {
                  "score": 4.5,
                  "departmentId": [1],
                  "projectId": [3],
                  "reviewDate": "2021-01-15"
                }
                """;
        mockMvc.perform(post("/api/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }


    @Test
    public void testGetEmployeeDetails() throws Exception {
        Long employeeId = 1L;
        EmployeeDetailsResponse employeeDetails = new EmployeeDetailsResponse(employeeId, "Alice", "Engineering", List.of(), List.of());

        when(employeeService.getEmployeeDetails(employeeId)).thenReturn(employeeDetails);

        mockMvc.perform(get("/api/employees/{id}/details", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Alice"))
                .andExpect(jsonPath("$.departmentName").value("Engineering"));
    }

//    @Test
//    public void testGetEmployeeDetails_NotFound() throws Exception {
//        Long employeeId = 1L;
//
//        Mockito.when(employeeService.getEmployeeDetails(employeeId)).thenReturn(null);
//
//        mockMvc.perform(get("/api/employees/{id}/details", employeeId))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.statusCode").value(404))
//                .andExpect(jsonPath("$.message").value("Employee not found with id : '1'"));
//    }
}