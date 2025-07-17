package com.example.epms_dr.repository;

import com.example.epms_dr.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p JOIN EmployeeProject ep ON ep.project.id = p.id WHERE ep.employee.id = :employeeId")
    List<Project> findByEmployeeId(@Param("employeeId") Long employeeId);
}
