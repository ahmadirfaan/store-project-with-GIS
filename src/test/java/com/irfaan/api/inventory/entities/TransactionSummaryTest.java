package com.irfaan.api.inventory.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionSummaryTest {

    @Test
    void getterSetterTest() {
        int id = 1;
        int quantity = 1;
        long totalPrice = 1L;
        LocalDateTime localdate = LocalDateTime.now();

        TransactionSummary transaction = new TransactionSummary();
        transaction.setDateTransaction(localdate);
        transaction.setTransactionId(id);
        transaction.setQuantity(quantity);
        transaction.setItemId(id);
        transaction.setTotalPriceTransaction(totalPrice);

        assertNotNull(transaction.getDateTransaction());
        assertNotNull(transaction.getTransactionId());
        assertNotNull(transaction.getQuantity());
        assertNotNull(transaction.getItemId());
        assertNotNull(transaction.getTotalPriceTransaction());
    }
}
