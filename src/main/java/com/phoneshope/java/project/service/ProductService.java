package com.phoneshope.java.project.service;


import com.phoneshope.java.project.dto.PriceDto;
import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductService {

    Product save(Product product);

    Product getById (Long id);

    Product getByyModelIdAndColorId(Long modelID , Long ColorId);

    void importProduct(ProductImportDto importDto);

    void setSalePrice(Long productId, BigDecimal price);

    void validateStock(Long productId, Integer numberOfUnit);

    Map<Integer, String> uploadProduct(MultipartFile file);
}
