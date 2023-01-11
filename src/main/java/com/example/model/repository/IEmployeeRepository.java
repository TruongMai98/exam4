package com.example.model.repository;

import com.example.model.entity.Branch;
import com.example.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Iterable<Employee> findAllByBranch(Branch branch);
    @Query(nativeQuery = true,
            value = "SELECT * FROM employees ORDER BY age")
    List<Employee> sortEmployeeListByAge();
}
