package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockElementTest {

    @Mock
    private ItemElement item;

    @Test
    void getterSetterTest() {
        Integer id = 1;
        String name = "x";
        Integer quantity = 1;

        StockElement stock = new StockElement();
        stock.setId(id);
        stock.setQuantity(1);
        stock.setItem(item);

        assertEquals(id, stock.getId());
        assertEquals(quantity, stock.getQuantity());
        assertEquals(item, stock.getItem());
    }
}
