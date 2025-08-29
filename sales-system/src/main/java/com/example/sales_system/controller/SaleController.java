package com.example.sales_system.controller;

import com.example.sales_system.entity.Sale;
import com.example.sales_system.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    @PutMapping("/transactions/{id}")
    public Sale updateTransaction(@PathVariable Long id,
                                  @RequestParam int quantity,
                                  @RequestParam double price) {
        return saleService.editTransaction(id, quantity, price);
    }
}
