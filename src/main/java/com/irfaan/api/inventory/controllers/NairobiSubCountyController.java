package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.NairobiSubCounty;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.models.PageSearch;
import com.irfaan.api.inventory.models.PagedList;
import com.irfaan.api.inventory.models.ResponseMessage;
import com.irfaan.api.inventory.services.NairobySubCountyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/location/nairobi")
@RestController
public class NairobiSubCountyController {

    @Autowired
    private NairobySubCountyService service;

    @DeleteMapping("/{id}")
    public ResponseMessage<NairobiSubCounty> removeById(@PathVariable Integer id) throws NotFoundException {
        NairobiSubCounty entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }


    @GetMapping()
    public ResponseMessage<PagedList<NairobiSubCounty>> findAll(
            @Valid PageSearch pageSearch
    ) {
        NairobiSubCounty nairobiSubCounty = new NairobiSubCounty();
        Page<NairobiSubCounty> entityPage = service.findAll(nairobiSubCounty, pageSearch.getPage(), pageSearch.getSize(),
                pageSearch.getSort());
        List<NairobiSubCounty> entities = entityPage.toList();
        PagedList<NairobiSubCounty> data = new PagedList<>(
                entities,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<NairobiSubCounty> findbyId(@PathVariable Integer id) throws NotFoundException {
        NairobiSubCounty data = service.findById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(data);
    }
}
