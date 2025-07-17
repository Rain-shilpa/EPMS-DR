package com.example.epms_dr.model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeListRequest {

    private Double score;
    private LocalDate reviewDate;
    private List<Long> departmentId;
    private List<Long> projectId;
}
