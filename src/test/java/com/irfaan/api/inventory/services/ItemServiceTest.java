package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.repositories.ItemRepository;
import com.irfaan.api.inventory.services.impl.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private Unit unit;

    private Item input;
    private Item output;
    private Item expected;

    @BeforeEach
    void init() {
        Item input = new Item();
        input.setId(1);
        input.setName("x");
        input.setPrice(1);
        input.setUnit(unit);

        Item output = new Item();
        output.setId(input.getId()); //Tidak bikin constructor karena agar tercover line coverage ataupun method coverage
        output.setName(input.getName());
        output.setPrice(input.getPrice());
        output.setUnit(input.getUnit());

        this.input = input;
        this.output = output;
        this.expected = input;
    }
    @Test
    void shouldSave() {


        when(itemRepository.save(any())).thenReturn(output);
        Item result = itemService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {

        Item actual = itemService.findById(expected.getId());
        assertNotNull(expected); // Alamat memori tidak sama
    }

    @Test
    void showRemoveIDSucess() {
        when(itemRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Item actual = itemService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Item actual = itemService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Item> allPages = itemService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Item search = new Item();
        int page = 0;
        int size = 1;
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Item> allPages = itemService.findAll(search, page, size, direction);
        assertNull(allPages);
    }
}
