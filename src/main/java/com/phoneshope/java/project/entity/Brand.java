package com.phoneshope.java.project.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "brand_id")
    private Integer id;
    @Column(name = "brand_name")
    private  String name;
}