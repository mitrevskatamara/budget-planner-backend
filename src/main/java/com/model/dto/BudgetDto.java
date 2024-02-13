package com.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Month;

@Data
@Builder
public class BudgetDto {

    private Month month;

    private int year;

    private Double budget;

    private String username;

    private String currency;
}