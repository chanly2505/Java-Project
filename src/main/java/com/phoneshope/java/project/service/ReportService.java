package com.phoneshope.java.project.service;

import com.phoneshope.java.project.dto.ExpenseReportDto;
import com.phoneshope.java.project.dto.ProductReportDto;
import com.phoneshope.java.project.service.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);

    List<ProductReportDto> getProductReport(LocalDate startDate , LocalDate endDate);

    List<ExpenseReportDto> getExpenseReport(LocalDate startDate , LocalDate endDate);
}
