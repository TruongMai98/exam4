package com.example.model.service;

import com.example.model.entity.Employee;
import com.example.model.entity.Branch;

import java.util.List;

public interface IEmployeeService extends IGeneralService<Employee>{
    Iterable<Employee> findAllByBranch(Branch branch);
    List<Employee> sortEmployeeListByAge();
}
