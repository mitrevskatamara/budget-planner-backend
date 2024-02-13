package com.model.dto;

import com.model.Budget;
import com.model.User;
import lombok.Data;

import java.time.format.TextStyle;
import java.util.Locale;

@Data
public class BudgetDTOList {
    private Long id;

    private User user;

    private String month;

    private int year;

    private Double balance;

    private Double budget;

    private String currency;

    public BudgetDTOList(Budget budget) {
        this.id = budget.getId();
        this.user = budget.getUser();
        this.month = budget.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.year = budget.getYear();
        this.budget = budget.getBudget();
        this.balance = budget.getBalance();
        this.currency = budget.getCurrency();
    }
}
