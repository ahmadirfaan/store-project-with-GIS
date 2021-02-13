package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionRequestTest {

    @Mock
    private DetailTransactionRequest dt;

    @Test
    void getterSetterTest() {
        List<DetailTransactionRequest> list = new ArrayList<>();
        list.add(dt);
        list.add(dt);
        list.add(dt);
        LocalDateTime date = LocalDateTime.now();
        int id = 1;

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setId(id);
        transactionRequest.setDateTransaction(date);
        transactionRequest.setDetailTransactionList(list);
        assertEquals(id, transactionRequest.getId());
        assertEquals(date, transactionRequest.getDateTransaction());
        assertEquals(list, transactionRequest.getDetailTransactionList());
    }
}
