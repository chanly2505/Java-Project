package com.phoneshope.java.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(generator = "model_seq_generator")
    @SequenceGenerator(name = "model_seq_generator",initialValue = 1,sequenceName = "model_seq")
    private Long id;
    private  String name;

    @ManyToOne
    @JoinColumn(name = "brandId")
    private  Brand brand;

}