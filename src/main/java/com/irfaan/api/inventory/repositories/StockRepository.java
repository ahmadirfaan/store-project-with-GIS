package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer>, StockSummaryRepository {

}
