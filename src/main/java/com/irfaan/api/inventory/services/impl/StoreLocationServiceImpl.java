package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.StoreLocation;
import com.irfaan.api.inventory.repositories.StoreLocationRepository;
import com.irfaan.api.inventory.services.StoreLocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreLocationServiceImpl extends CommonServiceImpl<StoreLocation, Integer> implements StoreLocationService {

    public StoreLocationServiceImpl(StoreLocationRepository repository) {
        super(repository);
    }

    @Override
    public List<StoreLocation> findAllStoreWithinSubCounty(Integer subCountyId) {
        return ((StoreLocationRepository) repository).findAllStoreLocationWithInSubCounty(subCountyId);
    }

    @Override
    public List<StoreLocation> findAllStoreByDistanceFromUser(Double userLongitude, Double userLattitude) {
        return ((StoreLocationRepository) repository).findAllStoreByDistanceFromUser(userLongitude, userLattitude);
    }

}