package com.phoneshope.java.project.controller;


import com.phoneshope.java.project.dto.ProductDTO;
import com.phoneshope.java.project.dto.SaleDTO;
import com.phoneshope.java.project.service.ProductService;
import com.phoneshope.java.project.service.SaleService;
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

    private  final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleDTO saleDTO){
        saleService.sell(saleDTO);
        return ResponseEntity.ok().build();
    }
}