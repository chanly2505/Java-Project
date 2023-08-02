package com.phoneshope.java.project.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductImportDto {

    @NotNull(message = "Product Id can't bee null ")
    private Long productId;

    @Min(value = 1, message = "ImportUnit must be greater than 0")
    private Integer importUnit;

    @DecimalMin(value = "0.000001", message = "Price must be greater than 0")
    private BigDecimal importPrice;

    @NotNull(message = "ImportDate can't be null")
    private LocalDate importDate;
}