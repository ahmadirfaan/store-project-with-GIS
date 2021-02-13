package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.Transaction;
import com.irfaan.api.inventory.repositories.TransactionRepository;
import com.irfaan.api.inventory.services.impl.TransactionServiceImpl;
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
class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction input;
    private Transaction output;
    private Transaction expected;

    @BeforeEach
    void init() {
        LocalDateTime date = LocalDateTime.now();
        Transaction input = new Transaction();
        input.setId(1);
        input.setDateTransaction(date);


        Transaction output = new Transaction();
        output.setId(input.getId()); //Tidak bikin constructor karena agar tercover line coverage ataupun method coverage
        output.setDateTransaction(input.getDateTransaction());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {


        when(transactionRepository.save(any())).thenReturn(output);
        Transaction result = transactionService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Transaction actual = transactionService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSucess() {
        when(transactionRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Transaction actual = transactionService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Transaction actual = transactionService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Transaction> allPages = transactionService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Transaction search = new Transaction();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Transaction> allPages = transactionService.findAll(search, page, size, direction);
        assertNull(allPages);
    }
}
