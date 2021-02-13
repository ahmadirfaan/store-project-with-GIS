package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockRequestTest {

    @Test
    void getterSetterTest() {
        int itemId = 1;
        int quantity = 1;

        StockRequest stockRequest = new StockRequest();
        stockRequest.setItemId(itemId);
        stockRequest.setQuantity(quantity);

        assertEquals(itemId, stockRequest.getItemId());
        assertEquals(quantity, stockRequest.getQuantity());
    }
}
