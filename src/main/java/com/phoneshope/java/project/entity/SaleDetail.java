package com.phoneshope.java.project.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "salesDetails")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sale_detail_id")
    private Long id;

    @Column(name = "sold_date")
    private LocalDateTime soldDate;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JoinColumn(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "unit")
    private Integer unit;
}