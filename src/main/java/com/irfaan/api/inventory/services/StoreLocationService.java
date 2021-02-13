package com.irfaan.api.inventory.services;

import com.irfaan.api.inventory.entities.StoreLocation;

import java.util.List;


public interface StoreLocationService extends CommonService<StoreLocation, Integer> {


    List<StoreLocation> findAllStoreWithinSubCounty(Integer subCountyId);
    List<StoreLocation> findAllStoreByDistanceFromUser(Double userLongitude, Double userLattitude);
}
