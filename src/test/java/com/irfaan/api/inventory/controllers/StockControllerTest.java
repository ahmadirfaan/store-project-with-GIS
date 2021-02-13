package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.models.ItemResponse;
import com.irfaan.api.inventory.models.StockRequest;
import com.irfaan.api.inventory.models.StockResponse;
import com.irfaan.api.inventory.models.UnitModel;
import com.irfaan.api.inventory.services.ItemService;
import com.irfaan.api.inventory.services.StockService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private StockService stockService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private ModelMapper modelMapper;

    private Stock entity;
    private StockRequest stockRequest;
    private Unit unit;
    private Item item;
    private StockResponse response;

    @BeforeEach
    void init() {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("X");

        Item item = new Item();
        item.setId(1);
        item.setName("x");
        item.setPrice(1);
        item.setUnit(unit);

        itemService.save(item);

        Stock entity = new Stock();
        entity.setId(1);
        entity.setItem(item);
        entity.setQuantity(5);

        StockRequest stockRequest = new StockRequest();
        stockRequest.setItemId(entity.getId());
        stockRequest.setQuantity(entity.getQuantity());

        UnitModel unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setName(item.getName());
        itemResponse.setPrice(item.getPrice());
        itemResponse.setUnit(unitModel);

        StockResponse response = new StockResponse();
        response.setId(entity.getId());
        response.setQuantity(entity.getQuantity());
        response.setItem(itemResponse);

        this.unit = unit;
        this.item = item;
        this.entity = entity;
        this.response = response;
    }

    @Test
    void addShouldSuccess() throws Exception {
        when(itemService.save(any())).thenReturn(item);
        when(stockService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(StockRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = post("/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": 1, \"quantity\": 5}");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(stockService.save(any())).thenReturn(entity);
        when(stockService.findById(entity.getId())).thenReturn(entity);
        when(modelMapper.map(any(StockRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = put("/stocks/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": 1, \"quantity\": 5}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {
        when(stockService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);
        when(stockService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/stocks/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    void RemoveByIdShouldSuccess() throws Exception {
        when(stockService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Stock.class), any(Class.class))).thenReturn(response);
        when(stockService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = delete("/stocks/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entitypage = Page.empty();
        when(stockService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(entitypage);
        RequestBuilder request = get("/stocks");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void findAllSummariesShouldReturnEmptyList() throws Exception {
        List<StockSummary> stockSummaries = new ArrayList<>();
        when(stockService.findAllSummaries()).thenReturn(stockSummaries);
        RequestBuilder request = get("/stocks/summaries");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }



}
