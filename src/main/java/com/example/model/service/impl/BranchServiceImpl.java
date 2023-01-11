package com.example.model.service.impl;

import com.example.model.entity.Branch;
import com.example.model.repository.IBranchRepository;
import com.example.model.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchServiceImpl implements IBranchService {
    @Autowired
    private IBranchRepository branchRepository;

    @Override
    public Iterable<Branch> findAll() {
        return branchRepository.findAll();
    }

    @Override
    public Optional<Branch> findById(Integer id) {
        return branchRepository.findById(id);
    }

    @Override
    public void save(Branch branch) {
        branchRepository.save(branch);
    }

    @Override
    public void remove(Integer id) {
        branchRepository.deleteById(id);
    }
}
