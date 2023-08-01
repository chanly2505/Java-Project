package com.phoneshope.java.project.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productImportHistories")
public class ProductImportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_Id")
    private Long id;

    @Column(name = "date_Import")
    private LocalDate dateImport;

    @Column(name = "import_Unit")
    private  Integer importUnit;

    @Column(name = "price_per_Unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}