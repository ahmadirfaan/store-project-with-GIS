package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.models.*;
import com.irfaan.api.inventory.services.ItemService;
import com.irfaan.api.inventory.services.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<StockResponse> add(
            @RequestBody @Valid StockRequest model) {

        Stock entity = modelMapper.map(model , Stock.class);

        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);
        entity = stockService.save(entity);
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<StockResponse> edit(@PathVariable Integer id, @RequestBody @Valid StockRequest model) {

        Stock entity = stockService.findById(id);
        if(entity == null) {
            throw new EntityNotFoundException();
        }
        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);

        modelMapper.map(model, entity);
        entity = stockService.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<StockResponse> removeById(@PathVariable Integer id) {
        Stock entity = stockService.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }


    @GetMapping()
    public ResponseMessage<PagedList<StockElement>> findAll(
            @Valid StockSearch stockSearch
    ) {
        Stock search = modelMapper.map(stockSearch, Stock.class);
        Page<Stock> entitiyPage = stockService.findAll(search, stockSearch.getPage(), stockSearch.getSize(),
                stockSearch.getSort());
        List<Stock> entities = entitiyPage.toList();

        List<StockElement> models = entities.stream()
                .map(e -> modelMapper.map(e, StockElement.class))
                .collect(Collectors.toList());
        PagedList<StockElement> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                models,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entitiyPage.getNumber(),                // total elements
                entitiyPage.getSize(),
                entitiyPage.getTotalElements());
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<StockResponse> findbyId(@PathVariable Integer id) {
        Stock entity = stockService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/summaries")
    public ResponseMessage<List<StockSummary>> findAllSummaries() {
        List<StockSummary> entityPage = stockService.findAllSummaries();
        return ResponseMessage.success(entityPage);
    }

    @GetMapping("/item/{id}")
    public ResponseMessage<StockSummary> findStockSummariesItem(@PathVariable Integer id) {
        StockSummary entity = stockService.findSummaryByItemId(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }


}
