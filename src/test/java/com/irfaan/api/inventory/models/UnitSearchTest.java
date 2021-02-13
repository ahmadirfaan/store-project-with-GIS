package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction;

class UnitSearchTest {

    @Test
    void getterSetterTest() {
        String code = "x";
        String description = "X";
        Integer page = 1;
        Integer size = 1;
        Direction sort = Direction.ASC;

        UnitSearch unit = new UnitSearch();
        unit.setCode(code);
        unit.setDescription(description);
        unit.setPage(page);
        unit.setSize(size);
        unit.setSort(sort);

        assertEquals(code, unit.getCode());
        assertEquals(description, unit.getDescription());
        assertEquals(page, unit.getPage());
        assertEquals(size, unit.getSize());
        assertEquals(sort, unit.getSort());
    }
}
