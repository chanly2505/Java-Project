package com.phoneshope.java.project.controller;


import com.phoneshope.java.project.dto.ProductDTO;
import com.phoneshope.java.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sell")
public class SaleController {

    private  final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok().build();
    }
}