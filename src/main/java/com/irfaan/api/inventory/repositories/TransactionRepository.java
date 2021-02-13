package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
