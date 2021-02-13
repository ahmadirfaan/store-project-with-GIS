package com.irfaan.api.inventory.models;

import com.irfaan.api.inventory.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DetailTransactionRequestTest {

    @Mock
    private ItemElement item;

    @Mock
    private Transaction transaction;

    @Test
    void getterSetterTest() {
        int id = 1;
        int quantity = 1;
        int totalPrice = 10;

        DetailTransactionRequest detailTransaction = new DetailTransactionRequest();
        detailTransaction.setId(id);
        detailTransaction.setItemId(id);
        detailTransaction.setQuantity(quantity);


        assertNotNull(detailTransaction.getId());
        assertNotNull(detailTransaction.getItemId());
        assertNotNull(detailTransaction.getQuantity());
    }

}
