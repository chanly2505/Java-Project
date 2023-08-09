package com.phoneshope.java.project.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Data
@Entity
@Table(name = "products",uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name" , unique = true)
    private  String name;

    @Column(name = "image_path")
    private  String imagePath;

    @Column(name = "available_Unit")
    private Integer availableUnit;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @DecimalMin(value = "0.000001", message = "Price must be greater than 0")
    @Column(name = "sale_Price")
    private BigDecimal salePrice;

}