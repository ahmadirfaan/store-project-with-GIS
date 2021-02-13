package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DetailTransactionElementTest {

    @Mock
    private ItemElement item;

    @Test
    void getterSetterTest() {
        int id = 1;
        int quantity = 1;
        int totalPrice = 10;
        LocalDateTime localdate = LocalDateTime.now();

        DetailTransactionElement detailTransaction = new DetailTransactionElement();
        detailTransaction.setId(id);
        detailTransaction.setItem(item);
        detailTransaction.setQuantity(quantity);
        detailTransaction.setTotalPrice(totalPrice);


        assertNotNull(detailTransaction.getId());
        assertNotNull(detailTransaction.getItem());
        assertNotNull(detailTransaction.getQuantity());
        assertNotNull(detailTransaction.getTotalPrice());
    }

}
