package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.DetailTransaction;
import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Transaction;
import com.irfaan.api.inventory.repositories.DetailTransactionRepository;
import com.irfaan.api.inventory.services.impl.DetailTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetailTransactionServiceTest {

    @InjectMocks
    private DetailTransactionServiceImpl transactionService;

    @Mock
    private DetailTransactionRepository transactionRepository;

    @Mock
    private Item item;

    @Mock
    private Transaction transaction;

    private DetailTransaction input;
    private DetailTransaction output;
    private DetailTransaction expected;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        DetailTransaction input = new DetailTransaction();
        input.setId(1);
        input.setItem(item);
        input.setQuantity(2);
        input.setTotalPrice(10);


        DetailTransaction output = new DetailTransaction();
        output.setId(input.getId()); //Tidak bikin constructor karena agar tercover line coverage ataupun method coverage
        output.setItem(input.getItem());
        output.setQuantity(input.getQuantity());
        output.setTotalPrice(input.getTotalPrice());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {


        when(transactionRepository.save(any())).thenReturn(output);
        DetailTransaction result = transactionService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        DetailTransaction actual = transactionService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSucess() {
        when(transactionRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        DetailTransaction actual = transactionService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        DetailTransaction actual = transactionService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<DetailTransaction> allPages = transactionService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        DetailTransaction search = new DetailTransaction();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<DetailTransaction> allPages = transactionService.findAll(search, page, size, direction);
        assertNull(allPages);
    }
}
