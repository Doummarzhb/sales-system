package com.example.sales_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransactionLogDTO {
    private Long id;
    private int oldQuantity;
    private int newQuantity;
    private double oldPrice;
    private double newPrice;
    private LocalDateTime updatedAt;
    private Long saleTransactionId;
}
