package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.ProductImportHistory;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.mapper.ProductMapper;
import com.phoneshope.java.project.repository.ProductImportRepository;
import com.phoneshope.java.project.repository.ProductRepository;
import com.phoneshope.java.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImportRepository productImportRepository;

    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        String name = "%s %s".formatted(product.getModel().getName()
        , product.getColor().getName()) ;
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product" , id));
    }

    @Override
    public void importProduct(ProductImportDto importDto) {
        //Update Available product unit
        Product product = getById(importDto.getProductId());
        Integer availableUnit= 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDto.getImportUnit());
        productRepository.save(product);

        //Save product import history
        ProductImportHistory importHistory =productMapper.toProductImportHistory(importDto,product);
        productImportRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product= getById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {

    }


}