package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction;

class TransactionSearchTest {

    @Test
    void getterSetterTest() {
        Integer page = 1;
        Integer size = 1;
        Direction sort = Direction.ASC;
        LocalDateTime date = LocalDateTime.now();


        TransactionSearch transactionSearch = new TransactionSearch();
        transactionSearch.setDate(date);
        transactionSearch.setPage(page);
        transactionSearch.setSize(size);
        transactionSearch.setSort(sort);

        assertEquals(date, transactionSearch.getDate());
        assertEquals(page, transactionSearch.getPage());
        assertEquals(size, transactionSearch.getSize());
        assertEquals(sort, transactionSearch.getSort());
    }
}
