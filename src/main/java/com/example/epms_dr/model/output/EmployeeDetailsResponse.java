package com.example.epms_dr.model.output;

import com.example.epms_dr.model.PerformanceReview;
import com.example.epms_dr.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsResponse {

    private Long employeeId;
    private String employeeName;
    private String departmentName;
    private List<Project> projects;
    private List<PerformanceReview> lastThreePerformanceReviews;
}
