package com.irfaan.api.inventory.entities;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UnitTest {

    @Test
    void getterSetterTest() {
        LocalDateTime localdate = LocalDateTime.now();

        Unit unit = new Unit();
        unit.setCreatedDate(localdate);
        unit.setModifiedDate(localdate);
        assertEquals(localdate, unit.getCreatedDate());
        assertEquals(localdate, unit.getModifiedDate());
    }

    @Test
    void toStringTest() {
        Unit unit = new Unit();
        String expected = unit.toString();
        String actual = "Unit{id=null, code='null', description='null'}";
        assertEquals(expected, actual);
    }
}
