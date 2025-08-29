package com.example.sales_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SaleTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Sale sale;


}
