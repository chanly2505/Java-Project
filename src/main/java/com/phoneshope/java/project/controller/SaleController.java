package com.phoneshope.java.project.controller;

import com.phoneshope.java.project.dto.SaleDTO;
import com.phoneshope.java.project.mapper.SaleMapper;
import com.phoneshope.java.project.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/{saleId}/cancel")
    public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
        saleService.cancelSale(saleId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getallsale")
    public ResponseEntity<?>listSale() {
        List<SaleDTO> saleDTOS= saleService.getAllSale().stream()
                .map(SaleMapper.INSTANCE::toEntity).toList();
        return  ResponseEntity.ok(saleDTOS);
    }
}