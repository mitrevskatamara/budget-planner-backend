package com.controller;

import com.model.Budget;
import com.model.User;
import com.model.dto.BudgetDTOList;
import com.model.dto.BudgetDto;
import com.model.dto.FilterDto;
import com.service.BudgetService;
import com.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("https://budget-planner-f424865111f8.herokuapp.com")
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = this.budgetService.getAll();

        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudget(@PathVariable Long id) {
        Budget budget = this.budgetService.getBudgetById(id);

        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Budget> createBudget(@RequestBody BudgetDto budgetDto) {
        User user = this.userService.findByUsername(budgetDto.getUsername());
        Budget budget =  this.budgetService.create(user, budgetDto);

        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto) {
        Budget budget = this.budgetService.update(id, budgetDto);

        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Budget>> filterBudgets(@RequestBody FilterDto filterDto) {
        User user = this.userService.findByUsername(filterDto.getUsername());
        List<Budget> budgets = this.budgetService.filterBudgets(user, filterDto);

        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @PostMapping("/filterByUser")
    public ResponseEntity<List<BudgetDTOList>> filterByUser(@RequestBody FilterDto filterDto) {
        User user = this.userService.findByUsername(filterDto.getUsername());
        List<BudgetDTOList> budgets = this.budgetService.findByUser(user);

        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }
}
