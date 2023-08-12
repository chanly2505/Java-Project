package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.SaleDTO;
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
        //TODO
        //Validation
        //validate(saleDTO);
        //Save
        //Deleted Data in database


    }

//    private void saveSale(SaleDTO saleDTO) {
//        saleDTO.getSaleDate();
//        Sale sale = new Sale();
//        sale.setSoldDate(saleDTO.getSaleDate());
//        saleRepository.save(sale);
//
//        //Sale Details
//        saleDTO.getProducts().forEach(ps -> {
//            SaleDetail saleDetail = new SaleDetail();
//            saleDetail.setAmount(null);
//        });
//    }
//
//    private void validate(SaleDTO saleDTO) {
//        saleDTO.getProducts().forEach(ps -> {
//            Product product = productService.getById(ps.getProductId());
//            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
//                throw new ApiException(HttpStatus.BAD_REQUEST, "Not [%s] EnoughProduct in stock".formatted(product.getName()));
//            }
//        });
//    }
}
//    private void validate2(SaleDTO saleDTO){
//       List<Long> productId = saleDTO.getProducts().stream()
//                .map(ProductSoldDTO::getProductId)
//                .toList();
//       productId.forEach(productService::getById);
//       Map<Long,Product> productMap =productRepository
//               .findAllById(productId)
//               .stream()
//               .collect(Collectors.toMap(Product::getId, Function.identity()));
//
//       saleDTO.getProducts().forEach(ps -> {
//       Product product =productMap.get(ps.getProductId());
//           if (product.getAvailableUnit() < ps.getNumberOfUnit()){
//               throw new ApiException(HttpStatus.BAD_REQUEST,"Not [%s] EnoughProduct in stock".formatted(product.getName()));
//           }
//        });
//    }
//}


