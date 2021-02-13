package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction;

class ItemSearchTest {
    @Test
    void getterSetterTest() {
        String name = "x";
        Integer page = 1;
        Integer size = 1;
        Direction sort = Direction.ASC;

        ItemSearch item = new ItemSearch();
        item.setName(name);
        item.setPage(page);
        item.setSize(size);
        item.setSort(sort);

        assertEquals(name, item.getName());
        assertEquals(page, item.getPage());
        assertEquals(size, item.getSize());
        assertEquals(sort, item.getSort());
    }
}
