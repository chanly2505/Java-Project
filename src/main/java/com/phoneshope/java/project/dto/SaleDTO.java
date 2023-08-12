package com.phoneshope.java.project.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {

    @NotEmpty
    private List <ProductSoldDTO> products;
    private LocalDate saleDate;
}