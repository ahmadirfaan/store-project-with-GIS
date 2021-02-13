package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.models.UnitModel;
import com.irfaan.api.inventory.services.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UnitController.class)
class  UnitControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ModelMapper modelMapper;

    private Unit entity;
    private UnitModel model;

    @BeforeEach
    void init() {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("xyz");
        entity.setDescription("XYZ");

        UnitModel model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());

        this.entity = entity;
        this.model = model;
    }
    @Test
    void addShouldSuccess() throws Exception {

        when(unitService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);

        RequestBuilder request = post("/units")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"xyz\", \"description\": \"XYZ\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(unitService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);
        when(unitService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/units/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(unitService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);
        when(unitService.findById(entity.getId())).thenReturn(entity);
        when(unitService.save(any())).thenReturn(entity);

        RequestBuilder request = put("/units/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"xyz\", \"description\": \"XYZ\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    void removeByIdShouldSuccess() throws Exception {

        when(unitService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Unit.class), any(Class.class))).thenReturn(model);
        when(unitService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = delete("/units/" + entity.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"xyz\", \"description\": \"XYZ\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entityPage = Page.empty();
        when(unitService.findAll(any(), anyInt(),  anyInt(), any())).thenReturn(entityPage);

        RequestBuilder request = get("/units");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }


}

