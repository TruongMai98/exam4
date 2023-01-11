package com.example.controller;

import com.example.model.entity.Branch;
import com.example.model.entity.Employee;
import com.example.model.service.IBranchService;
import com.example.model.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IBranchService branchService;

    @ModelAttribute("branches")
    public Iterable<Branch> provinces() {
        return branchService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employee", new Employee());
        modelAndView.addObject("message", "New employee created successfully");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        Iterable<Employee> employees = employeeService.findAll();
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        ModelAndView modelAndView;
        if (employee != null) {
            modelAndView = new ModelAndView("employee/edit");
            modelAndView.addObject("employee", employee.get());

        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("message", "Employee updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.findById(id);
        ModelAndView modelAndView;
        if (employee != null) {
            modelAndView = new ModelAndView("employee/delete");
            modelAndView.addObject("employee", employee.get());

        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(Employee employee) {
        employeeService.remove(employee.getId());
        return new ModelAndView("redirect:/employee/employees");
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) {
        return new ModelAndView("employee/view","employee", employeeService.findById(id).get());
    }
    @GetMapping("/sorted")
    public ModelAndView listSortedEmployees() {
        List<Employee> employees = employeeService.sortEmployeeListByAge();
        ModelAndView modelAndView = new ModelAndView("/employee/list", "employees", employees);
        return modelAndView;
    }
}
