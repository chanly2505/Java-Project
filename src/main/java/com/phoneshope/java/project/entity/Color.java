package com.phoneshope.java.project.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "color_id")
    private Long id;
    @Column(name = "color_name")
    private  String name;
}