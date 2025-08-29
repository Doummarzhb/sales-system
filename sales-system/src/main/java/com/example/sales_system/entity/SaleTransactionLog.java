package com.example.sales_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class SaleTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int oldQuantity;
    private int newQuantity;

    private double oldPrice;
    private double newPrice;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private SaleTransaction transaction;




    @PrePersist
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
