package com.example.springbootmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//bu klassni table bo'lishi kerakligini bildiradi
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    //pk bo'lishi kk
    @Id
    //auto increment va sequence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //ustun unga bo'lgan xamma xususiyatlar
    @Column(nullable = false, unique = true)
    private String name;

}