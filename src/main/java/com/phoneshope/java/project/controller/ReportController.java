package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.ExpenseReportDto;
import com.phoneshope.java.project.dto.ProductReportDto;
import com.phoneshope.java.project.service.ReportService;
import com.phoneshope.java.project.projection.ProductSold;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService  reportService;

    @GetMapping("{startDate}/{endDate}")
    public ResponseEntity<?> productSold (@DateTimeFormat(pattern = "yyyy-MM-dd")
                                              @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
       List<ProductSold> productSolds = reportService.getProductSold(startDate,endDate);
        return ResponseEntity.ok(productSolds);
    }
    @GetMapping("v2/{startDate}/{endDate}")
    public ResponseEntity<?> productSoldV2 (@DateTimeFormat(pattern = "yyyy-MM-dd")
                                          @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
        List<ProductReportDto> productSolds = reportService.getProductReport(startDate,endDate);
        return ResponseEntity.ok(productSolds);
    }
    @GetMapping("expense/{startDate}/{endDate}")
    public ResponseEntity<?> expenseReport (@DateTimeFormat(pattern = "yyyy-MM-dd")
                                            @PathVariable("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")@PathVariable("endDate") LocalDate endDate){
        List<ExpenseReportDto> productImportDto = reportService.getExpenseReport(startDate,endDate);
        return ResponseEntity.ok(productImportDto);
    }
}