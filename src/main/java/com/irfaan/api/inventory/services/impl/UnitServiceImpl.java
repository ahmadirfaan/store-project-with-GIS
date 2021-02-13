package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.Unit;
import com.irfaan.api.inventory.repositories.UnitRepository;
import com.irfaan.api.inventory.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl extends CommonServiceImpl<Unit, Integer> implements UnitService {

    @Autowired
    public UnitServiceImpl(UnitRepository repository) {
        super(repository);
    }
}
