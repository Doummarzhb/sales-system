package com.example.sales_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String category;

    private LocalDateTime creationDate;

    @PrePersist
    public void onCreate() {
        creationDate = LocalDateTime.now();
    }


}
