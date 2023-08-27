package com.phoneshope.java.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductReportDto {

    private Long productId;

    private String productName;

    private Integer unit;

    private BigDecimal getTotalAmount;
}