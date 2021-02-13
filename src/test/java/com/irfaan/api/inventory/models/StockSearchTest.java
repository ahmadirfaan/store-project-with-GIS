package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction;

class StockSearchTest {

    @Test
    void getterSetterTest() {
        Integer quantity = 1;
        Integer page = 1;
        Integer size = 1;
        Direction sort = Direction.ASC;

        StockSearch stock = new StockSearch();
        stock.setQuantity(quantity);
        stock.setPage(page);
        stock.setSize(size);
        stock.setSort(sort);

        assertEquals(quantity, stock.getQuantity());
        assertEquals(page, stock.getPage());
        assertEquals(size, stock.getSize());
        assertEquals(sort, stock.getSort());
    }
}
