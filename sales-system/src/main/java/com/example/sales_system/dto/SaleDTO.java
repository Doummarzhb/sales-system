package com.example.sales_system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    private Long id;
    private LocalDateTime creationDate;

    @NotNull(message = "Client ID is required")
    private Long clientId;

    @NotNull(message = "Seller is required")
    private String seller;

    private double total;

    @NotEmpty(message = "At least one sale transaction is required")
    @Valid
    private List<SaleTransactionDTO> transactions;
}
