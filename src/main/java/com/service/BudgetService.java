package com.service;

import com.model.Budget;
import com.model.Transaction;
import com.model.User;
import com.model.dto.BudgetDTOList;
import com.model.dto.BudgetDto;
import com.model.dto.FilterDto;
import com.model.dto.TransactionDto;

import java.time.Month;
import java.util.List;

public interface BudgetService {

    List<Budget> getAll();

    Budget getBudgetById(Long id);

    Budget create(User user, BudgetDto budgetDto);

    Budget update(Long id, BudgetDto budgetDto);

    Budget updateBudgetAfterAddTransaction(Transaction transaction);

    Budget updateBudgetAfterDeleteTransaction(Transaction transaction);

    Budget updateBudgetAfterUpdateTransaction(Transaction transaction, TransactionDto transactionDto);

    Budget findByUserAndMonthAndYear(User user, Month month, int year);

    List<BudgetDTOList> findByUser(User user);

    Boolean existsByMonthAndYear(Month month, int year, User user);

    List<Budget> filterBudgets(User user, FilterDto filterDto);
}
