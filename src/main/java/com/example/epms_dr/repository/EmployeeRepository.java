package com.example.epms_dr.repository;

import com.example.epms_dr.model.Employee;
import com.example.epms_dr.model.PerformanceReview;
import com.example.epms_dr.model.input.EmployeeListRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT DISTINCT e FROM Employee e " +
            "JOIN e.department d " +
            "LEFT JOIN EmployeeProject ep ON ep.employee = e " +
            "LEFT JOIN ep.project p " +
            "WHERE (:#{#filter.departmentId} IS NULL OR d.id IN :#{#filter.departmentId}) " +
            "AND (:#{#filter.projectId} IS NULL OR p.id IN :#{#filter.projectId}) " +
            "AND ((:#{#filter.reviewDate} IS NULL AND :#{#filter.score} IS NULL) OR e.id IN (" +
            "   SELECT pr.employee.id FROM PerformanceReview pr " +
            "   WHERE pr.reviewDate = :#{#filter.reviewDate} AND pr.score >= :#{#filter.score}" +
            "))")
    List<Employee> findFilteredEmployees(@Param("filter") EmployeeListRequest filter);

    Optional<Employee> findById(Long id);
}
