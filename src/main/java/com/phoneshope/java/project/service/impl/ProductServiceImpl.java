package com.phoneshope.java.project.service.impl;

import com.phoneshope.java.project.dto.ProductImportDto;
import com.phoneshope.java.project.entity.Product;
import com.phoneshope.java.project.entity.ProductImportHistory;
import com.phoneshope.java.project.exception.ApiException;
import com.phoneshope.java.project.exception.ResourceNotFoundException;
import com.phoneshope.java.project.mapper.ProductMapper;
import com.phoneshope.java.project.repository.ProductImportRepository;
import com.phoneshope.java.project.repository.ProductRepository;
import com.phoneshope.java.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public Product getByyModelIdAndColorId(Long modelID, Long colorId) {
        String text = "Product with model ID = %s and color ID = %d Not Found";
        return productRepository.findByModelIdAndColorId(modelID,colorId)
                .orElseThrow(()->new ApiException(HttpStatus.BAD_REQUEST,text.formatted(modelID,colorId)));
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

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet=workbook.getSheet("products");
            Iterator<Row> rowIterable= sheet.iterator();

            rowIterable.next();
            while (rowIterable.hasNext()){
                Integer rowNumber=0;
                try {
                    Row row = rowIterable.next();
                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber =(int)cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId=(long) cellModelId.getNumericCellValue();


                    Cell cellColorId = row.getCell(cellIndex++);
                    Long modelColorId=(long) cellColorId.getNumericCellValue();

                    Cell cellPriceImport = row.getCell(cellIndex++);
                    Double priceImport= cellPriceImport.getNumericCellValue();

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer modelImportUnit=(int) cellImportUnit.getNumericCellValue();
                    if (modelImportUnit < 1){
                        throw new ApiException(HttpStatus.NOT_FOUND, "Unit must be greater than 0");
                    }

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime modelImportDate= cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByyModelIdAndColorId(modelId,modelColorId);

                    Integer availableUnit= 0;
                    if (product.getAvailableUnit() != null){
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + modelImportUnit);
                    productRepository.save(product);

                    //Save product import history
                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setDateImport(modelImportDate);
                    importHistory.setImportUnit(modelImportUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(priceImport));
                    importHistory.setProduct(product);
                    productImportRepository.save(importHistory);
                } catch (Exception e) {
                    map.put(rowNumber,e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}