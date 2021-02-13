package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.models.ItemRequest;
import com.irfaan.api.inventory.models.ItemResponse;
import com.irfaan.api.inventory.models.UnitModel;
import com.irfaan.api.inventory.services.FileService;
import com.irfaan.api.inventory.services.ItemService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private FileService fileService;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ModelMapper modelMapper;

    private ItemRequest itemRequest;
    private Item entity;
    private Unit unit;
    private UnitModel unitModel;
    private ItemResponse response;


    @BeforeEach
    void init() {

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setName("x");
        itemRequest.setPrice(1);
        itemRequest.setUnitId(1);


        Item entity = new Item();
        entity.setId(1);
        entity.setName("x");
        entity.setPrice(1);


        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("X");
        entity.setUnit(unit);



        UnitModel unitModel = new UnitModel();
        unitModel.setId(itemRequest.getUnitId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        ItemResponse response = new ItemResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPrice(entity.getPrice());
        response.setUnit(unitModel);

        this.unit = unit;
        this.entity = entity;
        this.itemRequest = itemRequest;
        this.unitModel = unitModel;
        this.response = response;

    }

    @Test
    void addShouldSuccess() throws Exception {
        when(itemService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(ItemRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"x\", \"unitId\": 1, \"price\": 1}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(itemService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);
        when(itemService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/items/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    void RemoveByIdShouldSuccess() throws Exception {
        when(itemService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);
        when(itemService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = delete("/items/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    void editShouldSuccess() throws Exception {

        when(itemService.save(any())).thenReturn(entity);
        when(itemService.findById(entity.getId())).thenReturn(entity);
        when(modelMapper.map(any(ItemRequest.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = put("/items/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"x\", \"unitId\": 1, \"price\": 1}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entitypage = Page.empty();
        when(itemService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(entitypage);
        RequestBuilder request = get("/items");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void errorBadRequestTest() throws Exception {
        RequestBuilder request = post("/items")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("asdweessd");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())))
                .andExpect(result -> assertNotNull(result.getResolvedException()));
    }

    @Test
    void uploadShouldNotReturnResponse() throws Exception {
        RequestBuilder request = post("/items/2/image");
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void DownloadShouldNotReturnResponse() throws Exception {
        RequestBuilder request = get("/items/2/image");
        mvc.perform(request)
                .andExpect(status().isOk());
    }


}

