package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.DetailTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailTransactionRepository extends JpaRepository<DetailTransaction, Integer> {

}
