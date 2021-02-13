package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;

import java.util.List;

public interface StockService extends CommonService<Stock, Integer> {

    public List<StockSummary> findAllSummaries();

    public StockSummary findSummaryByItemId(Integer id);
}
