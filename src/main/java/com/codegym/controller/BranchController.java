package com.codegym.controller;

import com.codegym.model.Branch;
import com.codegym.model.Employee;
import com.codegym.service.IBranchService;
import com.codegym.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BranchController {
    @Autowired
    private IBranchService branchService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/branches")
    public ModelAndView listBranchs() {
        Iterable<Branch> branches = branchService.findAll();
        ModelAndView modelAndView = new ModelAndView("/branch/list", "branches", branches);
        return modelAndView;
    }

    @GetMapping("/create-branch")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/branch/create", "branch", new Branch());
        return modelAndView;
    }

    @PostMapping("/create-branch")
    public ModelAndView saveBranch(@ModelAttribute("branch") Branch branch) {
        branchService.save(branch);

        ModelAndView modelAndView = new ModelAndView("/branch/create","branch", new Branch());
        modelAndView.addObject("message", "New branch created successfully");
        return modelAndView;
    }


    @GetMapping("/view-branch/{id}")
    public ModelAndView viewBranch(@PathVariable("id") Long id) {
        Optional<Branch> branchOptional = branchService.findById(id);
        if (!branchOptional.isPresent()) {
            return new ModelAndView("/error.404");

        }
        Iterable<Employee> employees = employeeService.findAllByBranch(branchOptional.get());
        ModelAndView modelAndView = new ModelAndView("/branch/view");
        modelAndView.addObject("branch", branchOptional.get());
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }
}