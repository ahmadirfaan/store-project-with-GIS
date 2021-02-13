package com.irfaan.api.inventory.controllers;

import com.irfaan.api.inventory.entities.StoreLocation;
import com.irfaan.api.inventory.exceptions.EntityNotFoundException;
import com.irfaan.api.inventory.models.PageSearch;
import com.irfaan.api.inventory.models.PagedList;
import com.irfaan.api.inventory.models.ResponseMessage;
import com.irfaan.api.inventory.services.StoreLocationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storelocation")
public class StoreLocationController {

    @Autowired
    private StoreLocationService service;

    @DeleteMapping("/{id}")
    public ResponseMessage<StoreLocation> removeById(@PathVariable Integer id) throws NotFoundException {
        StoreLocation entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }


    @GetMapping()
    public ResponseMessage<PagedList<StoreLocation>> findAll(
            @Valid PageSearch pageSearch
    ) {
        StoreLocation storeLocation = new StoreLocation();
        Page<StoreLocation> entityPage = service.findAll(storeLocation, pageSearch.getPage(), pageSearch.getSize(),
                pageSearch.getSort());
        List<StoreLocation> entities = entityPage.toList();
        PagedList<StoreLocation> data = new PagedList<>(
                entities,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<StoreLocation> findbyId(@PathVariable Integer id) throws NotFoundException {
        StoreLocation data = service.findById(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(data);
    }

    @GetMapping("/withinsubcounty")
    public ResponseMessage<List<StoreLocation>> findAllStoreWithinSubCounty(@RequestParam("id") Integer subCountyId) {
        List<StoreLocation> data = service.findAllStoreWithinSubCounty(subCountyId);
        return ResponseMessage.success(data);
    }

    @GetMapping("/nearbystore")
    public ResponseMessage<List<StoreLocation>> findAllStoreByDistanceFromUser(@RequestParam("userlocation") List<Double> userLocation) {
        Double userLongitude = userLocation.get(0);
        Double userLattitude = userLocation.get(1);
        List<StoreLocation> stores = service.findAllStoreByDistanceFromUser(userLongitude, userLattitude);
        List<StoreLocation> data = stores.stream().peek(s -> s.setDistance(s.getDistance() * 111000.00)).collect(Collectors.toList());
        return ResponseMessage.success(data);
    }

}
