package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.models.PagedList;
import com.irfaan.api.inventory.models.ResponseMessage;
import com.irfaan.api.inventory.models.UnitModel;
import com.irfaan.api.inventory.models.UnitSearch;
import com.irfaan.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/units")
@RestController
public class UnitController {


    @Autowired
    private UnitService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<UnitModel> add(
            @RequestBody @Valid UnitModel model) {

        Unit entity = modelMapper.map(model , Unit.class);
        entity = service.save(entity);

        UnitModel data = modelMapper.map(entity , UnitModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<UnitModel> edit(@PathVariable Integer id, @RequestBody @Valid UnitModel model) {

        Unit entity = service.findById(id);
        if(entity == null) {
            throw new EntityNotFoundException();
        }


        modelMapper.map(model, entity);
        entity = service.save(entity);

        UnitModel data = modelMapper.map(entity, UnitModel.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<UnitModel> removeById(@PathVariable Integer id) {
        Unit entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }


    @GetMapping()
    public ResponseMessage<PagedList<UnitModel>> findAll(
            @Valid UnitSearch unitSearch
            ) {
        Unit search = modelMapper.map(unitSearch, Unit.class);
        Page<Unit> entityPage = service.findAll(search, unitSearch.getPage(), unitSearch.getSize(),
                unitSearch.getSort());
        List<Unit> entities = entityPage.toList();
        List<UnitModel> models = entities.stream()
                .map(e -> modelMapper.map(e, UnitModel.class))
                .collect(Collectors.toList());
        PagedList<UnitModel> data = new PagedList<>(    //Berfungsi untuk mengeliminasi data" pada JSON yang ditampilkan agar lebih informatif
                models,                                 // Dengan menyeleksi kebutuhan field data pada JSON yang diambil adalah page, size dan
                entityPage.getNumber(),                // total elements
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<UnitModel> findbyId(@PathVariable Integer id) {
        Unit entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }


}
