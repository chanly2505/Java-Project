package com.phoneshope.java.project.service;

import com.phoneshope.java.project.dto.SaleDTO;
import com.phoneshope.java.project.entity.Color;
import com.phoneshope.java.project.entity.Sale;

import java.util.List;

public interface SaleService {

    void sell(SaleDTO saleDTO);
    List<Sale> getAllSale();
    Sale getByID (Long saleId);
    void cancelSale(Long saleId);
}
