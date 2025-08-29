package com.example.sales_system.service;

import com.example.sales_system.entity.Sale;
import com.example.sales_system.entity.SaleTransaction;
import com.example.sales_system.entity.SaleTransactionLog;
import com.example.sales_system.repository.SaleRepository;
import com.example.sales_system.repository.SaleTransactionLogRepository;
import com.example.sales_system.repository.SaleTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleTransactionRepository transactionRepository;

    @Autowired
    private SaleTransactionLogRepository logRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale createSale(Sale sale) {
        // Set creation date
        sale.setCreationDate(LocalDateTime.now());

        // Set parent relation and calculate total
        double total = 0;
        for (SaleTransaction transaction : sale.getTransactions()) {
            transaction.setSale(sale);
            total += transaction.getPrice() * transaction.getQuantity();
        }
        sale.setTotal(total);

        return saleRepository.save(sale);
    }

    public Sale editTransaction(Long transactionId, int newQuantity, double newPrice) {
        SaleTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Log old values
        SaleTransactionLog log = new SaleTransactionLog();
        log.setTransaction(transaction);
        log.setOldQuantity(transaction.getQuantity());
        log.setOldPrice(transaction.getPrice());
        log.setNewQuantity(newQuantity);
        log.setNewPrice(newPrice);
        log.setUpdatedAt(LocalDateTime.now());
        logRepository.save(log);

        // Update transaction
        transaction.setQuantity(newQuantity);
        transaction.setPrice(newPrice);
        transactionRepository.save(transaction);

        // Recalculate sale total
        Sale sale = transaction.getSale();
        double newTotal = sale.getTransactions().stream()
                .mapToDouble(t -> t.getPrice() * t.getQuantity())
                .sum();
        sale.setTotal(newTotal);

        return saleRepository.save(sale);
    }
}
