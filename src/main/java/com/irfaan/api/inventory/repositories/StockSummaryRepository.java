package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.StockSummary;

import java.util.List;

public interface StockSummaryRepository {

    public List<StockSummary> findAllSummaries();
    public StockSummary findSummaryByItemId(Integer id);
}

