package com.example.controller;

import com.example.model.entity.Branch;
import com.example.model.entity.Employee;
import com.example.model.service.IBranchService;
import com.example.model.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private IBranchService branchService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/list")
    public ModelAndView list() {
        Iterable<Branch> branches = branchService.findAll();
        ModelAndView modelAndView = new ModelAndView("branch/list");
        modelAndView.addObject("branches", branches);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("branch/create");
        modelAndView.addObject("branch", new Branch());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("branch") Branch branch) {
        branchService.save(branch);

        ModelAndView modelAndView = new ModelAndView("branch/create");
        modelAndView.addObject("branch", new Branch());
        modelAndView.addObject("message", "New branch created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<Branch> branch = branchService.findById(id);
        ModelAndView modelAndView;
        if (branch.isPresent()) {
            modelAndView = new ModelAndView("branch/edit");
            modelAndView.addObject("branch", branch.get());

        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute("branch") Branch branch) {
        branchService.save(branch);
        ModelAndView modelAndView = new ModelAndView("branch/edit");
        modelAndView.addObject("branch", branch);
        modelAndView.addObject("message", "Branch updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Branch> branch = branchService.findById(id);
        ModelAndView modelAndView;
        if (branch.isPresent()) {
            modelAndView = new ModelAndView("branch/delete");
            modelAndView.addObject("branch", branch.get());

        } else {
            modelAndView = new ModelAndView("error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("branch") Branch branch) {
        branchService.remove(branch.getId());
        return "redirect:/branch/branches";
    }

    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") Integer id){
        Optional<Branch> branchOptional = branchService.findById(id);
        if(!branchOptional.isPresent()){
            return new ModelAndView("error-404");
        }

        Iterable<Employee> employees = employeeService.findAllByBranch(branchOptional.get());

        ModelAndView modelAndView = new ModelAndView("branch/view");
        modelAndView.addObject("branch", branchOptional.get());
        modelAndView.addObject("employees", employees);
        return modelAndView;
    }
}
