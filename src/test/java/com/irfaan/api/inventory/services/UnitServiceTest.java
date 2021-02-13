package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.repositories.UnitRepository;
import com.irfaan.api.inventory.services.impl.UnitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @InjectMocks
    private UnitServiceImpl unitService;

    @Mock
    private UnitRepository unitRepository;

    private Unit input;
    private Unit output;
    private Unit expected;


    @BeforeEach
    void init() {
        Unit input = new Unit();
        input.setId(1);
        input.setCode("xyz");
        input.setDescription("XYZ");

        Unit output = new Unit();
        output.setId(input.getId());
        output.setCode(input.getCode());
        output.setDescription(input.getDescription());

        this.input = input;
        this.output = output;
        this.expected = input;
    }

    @Test
    void shouldSave() {
        when(unitRepository.save(any())).thenReturn(output);
        Unit result = unitService.save(input);
        assertEquals(output, result);
    }

    @Test
    void showFindByID() {
        Unit actual = unitService.findById(expected.getId());
        assertNotNull(expected);
    }

    @Test
    void showRemoveIDSucess() {
        when(unitRepository.findById(input.getId())).thenReturn(Optional.ofNullable(input));
        Unit actual = unitService.removeById(input.getId());
        assertNotNull(actual);
    }

    @Test
    void showRemoveIDFail() {
        Unit actual = unitService.removeById(input.getId());
        assertNull(actual);
    }

    @Test
    void findAllSucess() {
        List<Unit> allPages = unitService.findAll();
        assertNotNull(allPages);
    }

    @Test
    void findAllPaginationNull() {
        Unit search = new Unit();
        int page = 0;
        int size = 1;
        Direction direction = Direction.ASC;
        Page<Unit> allPages = unitService.findAll(search, page, size, direction);
        assertNull(allPages);
    }

}
