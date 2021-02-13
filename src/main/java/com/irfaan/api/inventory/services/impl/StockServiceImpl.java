package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import com.irfaan.api.inventory.repositories.StockRepository;
import com.irfaan.api.inventory.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl extends CommonServiceImpl<Stock, Integer> implements StockService {

    @Autowired
    public StockServiceImpl(StockRepository repository) {
        super(repository);
    }

    @Override
    public List<StockSummary> findAllSummaries() {
        return ((StockRepository) repository).findAllSummaries();
    }

    @Override
    public StockSummary findSummaryByItemId(Integer id) {
        return ((StockRepository) repository).findSummaryByItemId(id);
    }
}
