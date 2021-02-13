package com.irfaan.api.inventory.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DetailTransactionTest {

    @Mock
    private Item item;

    @Mock
    private Transaction transaction;

    @Test
    void getterSetterTest() {
        int id = 1;
        int quantity = 1;
        int totalPrice = 10;
        LocalDateTime localdate = LocalDateTime.now();

        DetailTransaction detailTransaction = new DetailTransaction();
        detailTransaction.setId(id);
        detailTransaction.setItem(item);
        detailTransaction.setQuantity(quantity);
        detailTransaction.setCreatedDate(localdate);
        detailTransaction.setModifiedDate(localdate);
        detailTransaction.setTotalPrice(10);

        assertNotNull(detailTransaction.getId());
        assertNotNull(detailTransaction.getItem());
        assertNotNull(detailTransaction.getQuantity());
        assertNotNull(detailTransaction.getCreatedDate());
        assertNotNull(detailTransaction.getModifiedDate());
        assertNotNull(detailTransaction.getTotalPrice());
    }

    @Test
    void toStringTest() {
        DetailTransaction detailTransaction = new DetailTransaction();
        String detailTransactionToString = "DetailTransaction{id=null, totalPrice=null, quantity=null, item=null}";
        assertEquals( detailTransactionToString, detailTransaction.toString());
    }
}
