package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.NairobiSubCounty;
import com.irfaan.api.inventory.repositories.NairobiSubCountyRepository;
import com.irfaan.api.inventory.services.NairobySubCountyService;
import org.springframework.stereotype.Service;

@Service
public class NairobySubCountyServiceImpl
        extends CommonServiceImpl<NairobiSubCounty, Integer>
        implements NairobySubCountyService {

    public NairobySubCountyServiceImpl(NairobiSubCountyRepository repository) {
        super(repository);
    }
}
