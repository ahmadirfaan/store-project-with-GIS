package com.irfaan.api.inventory.entities;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemTest {

    @Test
    void getterSetterTest() {
        LocalDateTime localdate = LocalDateTime.now();

        Item item = new Item();
        item.setCreatedDate(localdate);
        item.setModifiedDate(localdate);
        assertEquals(localdate, item.getCreatedDate());
        assertEquals(localdate, item.getModifiedDate());
    }

    @Test
    void toStringTest() {
        Item item = new Item();
        String expected = item.toString();
        String actual = "Item{id=null, name=null, price=null, unit=null}";
        assertEquals(expected, actual);
    }
}
