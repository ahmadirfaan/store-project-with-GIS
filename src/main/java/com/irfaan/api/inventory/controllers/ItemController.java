package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.models.*;
import com.irfaan.api.inventory.services.FileService;
import com.irfaan.api.inventory.services.ItemService;
import com.irfaan.api.inventory.services.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/items")
@RestController
public class ItemController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Add Item", description = "Some Description...", tags = {"items"}) //Digunakan untuk memamnipulasi dalam
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucess"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseMessage.class))))
    })

    @PostMapping
    public ResponseMessage<ItemResponse> add(
            @RequestBody @Valid ItemRequest model) {

        Item entity = modelMapper.map(model , Item.class);

        Unit unit = unitService.findById(model.getUnitId());
        entity.setUnit(unit);
        entity = itemService.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

//    @PostMapping("/with-unit")
//    public ResponseMessage<ItemResponse> addWithUnit(
//            @RequestBody @Valid ItemResponse model) {
//
//        Item entity = modelMapper.map(model , Item.class);
//
//        entity = itemService.addWithUnit(entity);
//
//        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
//
//        return ResponseMessage.success(data);
//    }


    @PutMapping("/{id}")
    public ResponseMessage<ItemResponse> edit(@PathVariable Integer id, @RequestBody @Valid ItemRequest model) {

        Item entity = itemService.findById(id);
        if(entity == null) {
            throw new EntityNotFoundException();
        }

        Unit unit = unitService.findById(model.getUnitId());
        entity.setUnit(unit);

        modelMapper.map(model, entity); // ini harus diletakkan sebelum save diakibatkan manipulasi dari library model mapper
        entity = itemService.save(entity);

        ItemResponse data = modelMapper.map(entity, ItemResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ItemResponse> removeById(@PathVariable Integer id) {
        Item entity = itemService.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }


    @GetMapping()
    public ResponseMessage<PagedList<ItemResponse>> findAll(
            @Valid ItemSearch itemSearch
    ) {
        Item search = modelMapper.map(itemSearch, Item.class);
        Page<Item> entitiyPage = itemService.findAll(search, itemSearch.getPage(), itemSearch.getSize(),
                itemSearch.getSort());
        List<Item> entities = entitiyPage.toList();

        List<ItemResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, ItemResponse.class))
                .collect(Collectors.toList());
        PagedList<ItemResponse> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                models,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entitiyPage.getNumber(),                // total elements
                entitiyPage.getSize(),
                entitiyPage.getTotalElements());
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<ItemResponse> findbyId(@PathVariable Integer id) {
        Item entity = itemService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @PostMapping(value = "/{id}/image")
    public ResponseMessage upload(
            @PathVariable Integer id,
            @Valid ImageUploadRequest model) throws IOException {
        Item entity = itemService.findById(id);
        if(entity == null) {
            throw new EntityNotFoundException();
        }

        fileService.upload(entity, model.getFile().getInputStream());
        return ResponseMessage.success(true);
    }

    @GetMapping("/{id}/image")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Item entity = itemService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + entity.getId() + "\"");
        fileService.download(entity, response.getOutputStream());
    }

}
