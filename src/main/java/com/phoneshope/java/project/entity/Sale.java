package com.phoneshope.java.project.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sale_id")
    private Long id;

    @Column(name = "sold_date")
    private LocalDateTime soldDate;
}