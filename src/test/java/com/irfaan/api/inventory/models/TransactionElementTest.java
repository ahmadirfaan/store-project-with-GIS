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
class TransactionElementTest {

    @Mock
    private DetailTransactionElement dt;

    @Test
    void getterSetterTest() {
        LocalDateTime date = LocalDateTime.now();
        List<DetailTransactionElement> list = new ArrayList<>();
        list.add(dt);
        list.add(dt);
        list.add(dt);

        int id = 1;

        TransactionElement transactionElement = new TransactionElement();
        transactionElement.setId(id);
        transactionElement.setDateTransaction(date);
        transactionElement.setDetailTransactionList(list);
        assertEquals(id, transactionElement.getId());
        assertEquals(date, transactionElement.getDateTransaction());
        assertEquals(list, transactionElement.getDetailTransactionList());
    }
}
