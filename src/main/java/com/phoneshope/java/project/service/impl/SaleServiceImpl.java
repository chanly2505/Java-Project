package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.ProductSoldDTO;
import com.phoneshope.java.project.dto.SaleDTO;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.Sale;
import com.phoneshope.java.project.entity.SaleDetail;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.repository.ProductRepository;
import com.phoneshope.java.project.repository.SaleDetailRepository;
import com.phoneshope.java.project.repository.SaleRepository;
import com.phoneshope.java.project.service.ProductService;
import com.phoneshope.java.project.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sell(SaleDTO saleDTO) {
        List<Long> productsId = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        productsId.forEach(productService::getById);
        List<Product> products = productRepository.findAllById(productsId);
        Map<Long, Product> productMap = productRepository
                .findAllById(productsId)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //Sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //SaleDetail
        saleDTO.getProducts().forEach(ps->{
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail= new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);

            //Cut Stock
            Integer avaliableUnit=product.getAvailableUnit()- ps.getNumberOfUnit();
            product.setAvailableUnit(avaliableUnit);
            productRepository.save(product);
        });
    }

    @Override
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getByID(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() ->new ResourceNotFoundException("saleID" ,saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        //Update Sale Status
        Sale sale = getByID(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        //Update Stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
       List<Long> productsId = saleDetails.stream().map(sd -> sd.getProduct().getId()).toList();
        List<Product> products = productRepository.findAllById(productsId);
        Map<Long, Product> productMap = productRepository
                .findAllById(productsId)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        saleDetails.forEach(sd ->{
            Product product = productMap.get(sd.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);
        });
    }
}


