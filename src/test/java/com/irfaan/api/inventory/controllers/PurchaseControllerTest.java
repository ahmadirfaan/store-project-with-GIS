package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.DetailTransaction;
import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Transaction;
import com.irfaan.api.inventory.models.*;
import com.irfaan.api.inventory.services.*;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PurchaseController.class)
class PurchaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private DetailTransactionService detailTransactionService;

    @MockBean
    private StockService stockService;

    @MockBean
    private ModelMapper modelMapper;

    private TransactionRequest transactionRequest;
    private Transaction entity;
    private Transaction response;


    @BeforeEach
    void init() {

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setId(1);
        transactionRequest.setDateTransaction(LocalDateTime.now());

        DetailTransactionRequest detailTransactionRequest = new DetailTransactionRequest();
        detailTransactionRequest.setId(1);
        detailTransactionRequest.setItemId(1);
        detailTransactionRequest.setQuantity(1);

        List<DetailTransactionRequest> detailTransactionRequestList = new ArrayList<>();
        detailTransactionRequestList.add(detailTransactionRequest);
        detailTransactionRequestList.add(detailTransactionRequest);

        transactionRequest.setDetailTransactionList(detailTransactionRequestList);


        Transaction entity = new Transaction();
        entity.setId(1);
        entity.setDateTransaction(LocalDateTime.now());
        entity.setTotalPay(2.0);

        DetailTransaction detailTransaction = new DetailTransaction();
        detailTransaction.setId(1);
        Item item = new Item();
        item.setId(1);
        item.setName("x");
        item.setPrice(1);
        detailTransaction.setItem(item);
        detailTransaction.setQuantity(1);
        detailTransaction.setCreatedDate(LocalDateTime.now());
        detailTransaction.setModifiedDate(LocalDateTime.now());
        detailTransaction.setTotalPrice(10);

        List<DetailTransaction> detailTransactionList = new ArrayList<>();
        detailTransactionList.add(detailTransaction);
        detailTransactionList.add(detailTransaction);

        entity.setDetailTransactionList(detailTransactionList);

        this.entity = entity;
        this.response = entity;
        this.transactionRequest = transactionRequest;
    }

    @Test
    void addShouldFail() throws Exception {
        when(transactionService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(response);

        RequestBuilder request = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"detailTransactionList\": [ { \"quantity\": 100, \"itemId\": 1 }, {\"quantity\": 200, \"itemId\": 1 } ] }");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }

    @Test
    void findByIdShouldSuccess() throws Exception {

        when(transactionService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(response);
        when(transactionService.findById(entity.getId())).thenReturn(entity);

        RequestBuilder request = get("/transactions/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }

//    @Test
//    void RemoveByIdShouldSuccess() throws Exception {
//        when(transactionService.save(any())).thenReturn(entity);
//        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(response);
//        when(transactionService.removeById(entity.getId())).thenReturn(entity);
//
//        RequestBuilder request = delete("/transactions/" + response.getId().toString())
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
//                .andExpect(jsonPath("$.data.dateTransaction", is(entity.getDateTransaction())));
//    }

    @Test
    void RemoveByIdShouldFail() throws Exception {
        when(transactionService.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(response);
        when(transactionService.removeById(entity.getId())).thenReturn(entity);

        RequestBuilder request = delete("/transactions/" + response.getId().toString())
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));
    }


    @Test
    void findAllShouldReturnEmptyList() throws Exception {
        Page entitypage = Page.empty();
        when(transactionService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(entitypage);
        RequestBuilder request = get("/transactions");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    void errorBadRequestTest() throws Exception {
        RequestBuilder request = post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType("asdweessd");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())))
                .andExpect(result -> assertNotNull(result.getResolvedException()));
    }

}

