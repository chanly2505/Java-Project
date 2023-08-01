package com.phoneshope.java.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ProductImportDto {

    private Long productId;

    private Integer importUnit;

    private BigDecimal importPrice;

    private LocalDate importDate;
}