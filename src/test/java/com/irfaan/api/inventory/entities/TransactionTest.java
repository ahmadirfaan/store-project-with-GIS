package com.irfaan.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionTest {

    @Mock
    private DetailTransaction detailTransaction;

    @Test
    void getterSetterTest() {
        int id = 1;
        LocalDateTime date = LocalDateTime.now();
        double totalPay = 25.00;
        List<DetailTransaction> detailTransactionList = new ArrayList<>();
        detailTransactionList.add(detailTransaction);
        detailTransactionList.add(detailTransaction);
        detailTransactionList.add(detailTransaction);

        Transaction transaction = new Transaction();
        transaction.setDateTransaction(date);
        transaction.setId(id);
        transaction.setDetailTransactionList(detailTransactionList);
        transaction.setCreatedDate(date);
        transaction.setModifiedDate(date);
        transaction.setTotalPay(totalPay);

        assertEquals(id, transaction.getId());
        assertEquals(date, transaction.getDateTransaction());
        assertEquals(detailTransactionList, transaction.getDetailTransactionList());
        assertEquals(date, transaction.getCreatedDate());
        assertEquals(date, transaction.getModifiedDate());
        assertEquals(totalPay, transaction.getTotalPay());
    }

    @Test
    void toStringTest() {
        Transaction transaction = new Transaction();
        String transactionToString = "Transaction{id=null, dateTransaction=null, detailTransactionList=null, totalPay=null}";
        assertEquals( transactionToString, transaction.toString());
    }
}
