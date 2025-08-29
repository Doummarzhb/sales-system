package com.example.sales_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleModel {
    private Long id;
    private LocalDateTime creationDate;
    private Long clientId;
    private String clientName;
    private String seller;
    private double total;
    private List<SaleTransactionModel> transactions;
}
