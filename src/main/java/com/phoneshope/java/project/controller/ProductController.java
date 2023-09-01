package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.BrandDto;
import com.phoneshope.java.project.dto.PriceDto;
import com.phoneshope.java.project.dto.ProductDTO;
import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Brand;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.mapper.BrandMapper;
import com.phoneshope.java.project.mapper.ProductMapper;
import com.phoneshope.java.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

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
    @PostMapping("{productID}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productID, @RequestBody PriceDto priceDto){
        productService.setSalePrice(productID,priceDto.getPrice());
        return  ResponseEntity.ok().build();
    }
    @PostMapping("/uploadProduct")
    public  ResponseEntity<?> uploadProduct (@RequestParam("file") MultipartFile file ){
        Map<Integer ,String> errorMap =productService.uploadProduct(file);
        return  ResponseEntity.ok(errorMap);
    }
}