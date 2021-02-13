package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemElementTest {
    @Test
    void getterSetterTest() {
        Integer id = 1;
        String name = "x";
        Integer price = 1;

        ItemElement item = new ItemElement();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);
        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
    }
}
