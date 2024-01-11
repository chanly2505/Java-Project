package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.ExpenseReportDto;
import com.phoneshope.java.project.dto.ProductReportDto;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.ProductImportHistory;
import com.phoneshope.java.project.entity.SaleDetail;
import com.phoneshope.java.project.repository.ProductImportRepository;
import com.phoneshope.java.project.repository.ProductRepository;
import com.phoneshope.java.project.repository.SaleDetailRepository;
import com.phoneshope.java.project.repository.SaleRepository;
import com.phoneshope.java.project.service.ReportService;
import com.phoneshope.java.project.projection.ProductSold;
import com.phoneshope.java.project.spec.ProductImportHistoryFilter;
import com.phoneshope.java.project.spec.ProductImportHistorySpec;
import com.phoneshope.java.project.spec.SaleDetailFilter;
import com.phoneshope.java.project.spec.SaleDetailSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final SaleRepository saleRepository;

    private final SaleDetailRepository saleDetailRepository;

    private final ProductRepository productRepository;

    private final ProductImportRepository productImportRepository;

    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate,endDate);
    }

    @Override
    public List<ProductReportDto> getProductReport(LocalDate startDate, LocalDate endDate) {
        List<ProductReportDto> list = new ArrayList<>();
        SaleDetailFilter detailFilter = new SaleDetailFilter();
        detailFilter.setStartDate(startDate);
        detailFilter.setEndDate(endDate);
        Specification<SaleDetail> specification = new SaleDetailSpec(detailFilter);
        List<SaleDetail> saleDetailList = saleDetailRepository.findAll(specification);
        List<Long> productIds =saleDetailList.stream().map(sd -> sd.getProduct().getId()).toList();
        Map<Long , Product> productListMap =productRepository.findAllById(productIds)
                .stream().collect(Collectors.toMap(Product::getId,Function.identity()));
        Map<Product,List<SaleDetail>> saleDetailMap =saleDetailList.stream()
                .collect(Collectors.groupingBy(SaleDetail::getProduct));
        for(var entry:saleDetailMap.entrySet()){
           Product product = productListMap.get(entry.getKey().getId());
           List<SaleDetail> sdList =entry.getValue();
           //totalUnit
            Integer unit =sdList.stream().map(SaleDetail::getUnit)
                    .reduce(1 , (a,b) -> a+b);
            double totalAmount = sdList.stream().mapToDouble(sd -> sd.getUnit()* sd.getAmount().doubleValue())
                    .sum();
            ProductReportDto reportDto = new ProductReportDto();
            reportDto.setProductId(product.getId());
            reportDto.setProductName(product.getName());
            reportDto.setUnit(unit);
            reportDto.setGetTotalAmount(BigDecimal.valueOf(totalAmount));
            list.add(reportDto);
        }
        return list ;
    }

    @Override
    public List<ExpenseReportDto> getExpenseReport(LocalDate startDate, LocalDate endDate) {
        List<ExpenseReportDto> list = new ArrayList<>();
        ProductImportHistoryFilter  importHistoryFilter = new ProductImportHistoryFilter();
        importHistoryFilter.setStartDate(startDate);
        importHistoryFilter.setEndDate(endDate);
        ProductImportHistorySpec spec = new ProductImportHistorySpec(importHistoryFilter);
        List<ProductImportHistory> importHistories = productImportRepository.findAll(spec);
        Set<Long> productIds = importHistories.stream()
                .map(his -> his.getProduct().getId())
                .collect(Collectors.toSet());
       List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
        Map<Product, List<ProductImportHistory>> importMap = importHistories.stream()
                .collect(Collectors.groupingBy(ProductImportHistory::getProduct));
        var expenseReportDtos = new ArrayList<ExpenseReportDto>();
        for (var entry:importMap.entrySet()){
            Product product =productMap.get(entry.getKey().getId());
            List<ProductImportHistory> importHistoryList= entry.getValue();
            int totalUnit = importHistoryList.stream()
                    .mapToInt(pi -> pi.getImportUnit())
                    .sum();
            double totalAmount= importHistoryList.stream().mapToDouble(ip -> ip.getImportUnit() * ip.getPricePerUnit().doubleValue())
                    .sum();
            var expenseReportDto = new ExpenseReportDto();
            expenseReportDto.setProductId(product.getId());
            expenseReportDto.setProductName(product.getName());
            expenseReportDto.setPricePerUnit(product.getSalePrice());
            expenseReportDto.setTotalAmount(BigDecimal.valueOf(totalAmount));
            expenseReportDtos.add(expenseReportDto);
        }
        return expenseReportDtos;
    }
}