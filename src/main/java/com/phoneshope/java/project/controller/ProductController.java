package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.dto.ProductDTO;
import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.mapper.BrandMapper;
import com.phoneshope.java.project.mapper.ProductMapper;
import com.phoneshope.java.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private  final ProductMapper productMapper;


    @PostMapping
    public ResponseEntity<?> create (@RequestBody ProductDTO productDto){
        Product product =productMapper.toProduct(productDto) ;
        product= productService.save(product);
        return ResponseEntity.ok(product);
    }
    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDto importDto){
        productService.importProduct(importDto);
        return  ResponseEntity.ok().build();
    }

}