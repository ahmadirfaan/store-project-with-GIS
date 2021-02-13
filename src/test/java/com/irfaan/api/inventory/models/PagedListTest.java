package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PagedListTest {

    @Test
    void getterSetterTest() {
        List<String> list = new ArrayList<>();
        list.add("a");

        int page = 1;
        int size = 1;
        long total = 1;
        LocalDateTime time = LocalDateTime.now();
        PagedList<String> pagedList = new PagedList<>();
        pagedList.setList(list);
        pagedList.setPage(page);
        pagedList.setSize(size);
        pagedList.setTotal(total);

        assertNotNull(pagedList.getList());
        assertEquals(page, pagedList.getPage());
        assertEquals(size, pagedList.getSize());
        assertEquals(total, pagedList.getTotal());
    }
}
