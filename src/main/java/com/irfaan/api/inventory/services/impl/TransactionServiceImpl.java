package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.Transaction;
import com.irfaan.api.inventory.repositories.TransactionRepository;
import com.irfaan.api.inventory.services.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl
        extends CommonServiceImpl<Transaction, Integer>
        implements TransactionService {


    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }

}
