package com.phoneshope.java.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseReportDto {
    private Long productId;
    private String productName;
    private Integer totalUnit;
    private BigDecimal pricePerUnit;
    private BigDecimal totalAmount;
}