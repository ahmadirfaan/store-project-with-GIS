package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.*;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.exceptions.NotBalancedStockException;
import com.irfaan.api.inventory.models.*;
import com.irfaan.api.inventory.services.DetailTransactionService;
import com.irfaan.api.inventory.services.ItemService;
import com.irfaan.api.inventory.services.StockService;
import com.irfaan.api.inventory.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/transactions")
@RestController
public class PurchaseController {


    @Autowired
    private TransactionService transactionService;

    @Autowired
    private DetailTransactionService detailTransactionService;

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Purchase Item", description = "purchase item for many item", tags = {"items"}) //Digunakan untuk memamnipulasi dalam
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucess"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseMessage.class))))
    })
    @PostMapping
    public ResponseMessage<Transaction> add(
            @RequestBody @Valid TransactionRequest model) {

        Transaction entity = modelMapper.map(model , Transaction.class);
        LocalDateTime date = LocalDateTime.now();
        entity.setDateTransaction(date);
        Double totalPay = 0.0;
        List<DetailTransaction> detailTransactionList = new ArrayList<>();
        for (DetailTransactionRequest dt : model.getDetailTransactionList()) {
            DetailTransaction detailTransaction = new DetailTransaction();
            Item item = itemService.findById(dt.getItemId());
            StockSummary stockItem = stockService.findSummaryByItemId(dt.getItemId());
            if( (dt.getQuantity() > stockItem.getQuantity()) || (stockItem.getQuantity() < 0) ) {
                throw new NotBalancedStockException();
            } else {
                Stock stock = new Stock();
                stock.setItem(item);
                stock.setQuantity(-dt.getQuantity());
                stockService.save(stock);
                detailTransaction.setTotalPrice(item.getPrice() * dt.getQuantity());
                detailTransaction.setQuantity(dt.getQuantity());
                detailTransaction.setItem(item);
                totalPay = totalPay + detailTransaction.getTotalPrice();
                detailTransactionList.add(detailTransaction);
            }

        }
        entity.setDetailTransactionList(detailTransactionList);
        entity.setTotalPay(totalPay);
        entity = transactionService.save(entity);
        return ResponseMessage.success(entity);
    }


    @GetMapping("/{id}")
    public ResponseMessage<TransactionElement> findbyId(@PathVariable Integer id) {
        Transaction entity = transactionService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        TransactionElement data = modelMapper.map(entity, TransactionElement.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TransactionElement> removeById(@PathVariable Integer id) {
        Transaction entity = transactionService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        for (DetailTransaction dt : entity.getDetailTransactionList()) {
            detailTransactionService.removeById(dt.getId());
        }
        Transaction remove = transactionService.removeById(id);
        TransactionElement data = modelMapper.map(remove, TransactionElement.class);
        return ResponseMessage.success(data);
    }


    @GetMapping()
    public ResponseMessage<PagedList<TransactionElement>> findAll(
            @Valid TransactionSearch transactionSearch
    ) {
        Transaction search = modelMapper.map(transactionSearch, Transaction.class);
        Page<Transaction> entitiyPage = transactionService.findAll(search, transactionSearch.getPage(), transactionSearch.getSize(),
                transactionSearch.getSort());
        List<Transaction> entities = entitiyPage.toList();

        List<TransactionElement> models = entities.stream()
                .map(e -> modelMapper.map(e, TransactionElement.class))
                .collect(Collectors.toList());
        PagedList<TransactionElement> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                models,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entitiyPage.getNumber(),                // total elements
                entitiyPage.getSize(),
                entitiyPage.getTotalElements());
        return ResponseMessage.success(data);
    }


}
