package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.DetailTransaction;
import com.irfaan.api.inventory.repositories.DetailTransactionRepository;
import com.irfaan.api.inventory.services.DetailTransactionService;
import org.springframework.stereotype.Service;

@Service
public class DetailTransactionServiceImpl
        extends CommonServiceImpl<DetailTransaction, Integer>
        implements DetailTransactionService {


    public DetailTransactionServiceImpl(DetailTransactionRepository repository) {
        super(repository);
    }
}
