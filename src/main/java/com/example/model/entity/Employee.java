package com.example.model.entity;

import com.example.model.entity.Branch;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String employeeCode;
    private String name;
    private Integer age;
    private double salary;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Employee() {
    }

    public Employee(Integer id, String employeeCode, String name, Integer age, double salary, Branch branch) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
