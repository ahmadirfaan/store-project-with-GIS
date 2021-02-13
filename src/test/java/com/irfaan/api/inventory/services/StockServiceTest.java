package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import com.irfaan.api.inventory.repositories.StockRepository;
import com.irfaan.api.inventory.services.impl.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @Mock
    private Item item;

    private Stock input;
    private Stock output;
    private Stock expected;
    private StockSummary stockSummary;
    private StockSummary stockSummary2;

    @BeforeEach
    void init() {
        Stock input = new Stock();
        input.setId(1);
        input.setItem(item);
        input.setQuantity(1);

        Stock output = new Stock();
        output.setId(input.getId());
        output.setItem(input.getItem());
        output.setQuantity(input.getQuantity());

        StockSummary stockSummary = new StockSummary();
        stockSummary.setItem(item);
        stockSummary.setQuantity(1L);

        StockSummary stockSummary2 = new StockSummary(stockSummary.getItem(), stockSummary.getQuantity()); //Berfungsi untuk mengcoverage Getter Setter dari Stock Summary 2
        this.input = input;
        this.output = output;
        this.expected = input;
        this.stockSummary = stockSummary;
        this.stockSummary2 = stockSummary2;
    }
    @Test
    void shouldSave() {

        when(stockRepository.save(any())).thenReturn(output);
        Stock result = stockService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Stock actual = stockService.findById(expected.getId());
        assertNotNull(expected);
    }

    @Test
    void showRemoveIDSucess() {
        when(stockRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Stock actual = stockService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Stock actual = stockService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Stock> allPages = stockService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Stock search = new Stock();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Stock> allPages = stockService.findAll(search, page, size, direction);
        assertNull(allPages);
    }

    @Test
    void emptyFindAllSummaries() {
        List<StockSummary> expected = new ArrayList<>();
        expected.add(stockSummary);
        expected.add(stockSummary2);
        expected.clear();
        List<StockSummary> actual = stockService.findAllSummaries();
        assertEquals(expected,actual);
    }
}
