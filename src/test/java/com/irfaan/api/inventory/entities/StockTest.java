package com.irfaan.api.inventory.entities;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StockTest {

    @Test
    void getterSetterTest() {
        LocalDateTime localdate = LocalDateTime.now();

        Stock stock = new Stock();
        stock.setCreatedDate(localdate);
        stock.setModifiedDate(localdate);
        assertEquals(localdate, stock.getCreatedDate());
        assertEquals(localdate, stock.getModifiedDate());
    }

    @Test
    void toStringTest() {
        Stock stock = new Stock();
        String expected = stock.toString();
        String actual = "Stock{id=null, item=null, quantity=null}";
        assertEquals(expected, actual);
    }
}
