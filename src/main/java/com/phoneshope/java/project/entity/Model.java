package com.phoneshope.java.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private  Brand brand;

}