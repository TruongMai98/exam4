package com.example.formatter;

import com.example.model.entity.Branch;
import com.example.model.service.IBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class BranchFormatter implements Formatter<Branch> {

    private IBranchService branchService;
    @Autowired
    public BranchFormatter(IBranchService branchService) {
        this.branchService = branchService;
    }

    @Override
    public Branch parse(String text, Locale locale) throws ParseException {
        Optional<Branch> provinceOptional = branchService.findById(Integer.parseInt(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Branch object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
