package com.example.sales_system.repository;

import com.example.sales_system.entity.SaleTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleTransactionLogRepository extends JpaRepository<SaleTransactionLog, Long> {}