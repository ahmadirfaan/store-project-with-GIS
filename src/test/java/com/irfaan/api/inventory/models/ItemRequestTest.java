package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRequestTest {

    @Test
    void getterSetterTest() {
        String name = "a";
        int price = 1;
        int unitId = 1;

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName(name);
        itemRequest.setPrice(price);
        itemRequest.setUnitId(unitId);

        assertNotNull(itemRequest.getName());
        assertEquals(price, itemRequest.getPrice());
        assertEquals(unitId, itemRequest.getUnitId());
    }
}
