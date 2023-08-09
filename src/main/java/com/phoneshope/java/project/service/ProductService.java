package com.phoneshope.java.project.service;


import com.phoneshope.java.project.dto.PriceDto;
import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Product;

import java.math.BigDecimal;

public interface ProductService {

    Product save(Product product);

    Product getById (Long id);

    void importProduct(ProductImportDto importDto);

    void setSalePrice(Long productId, BigDecimal price);
}
