package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.StoreLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreLocationRepository extends JpaRepository<StoreLocation, Integer> {

    @Query(value = "SELECT sln.id, sln.name, sln.geom, null as distance FROM store_location_nairobi sln, nairobi_sub_counties nsc " +
            "WHERE ST_Within(sln.geom, nsc.geom) AND nsc.id= :subCountyId", nativeQuery = true)
    List<StoreLocation> findAllStoreLocationWithInSubCounty(@Param("subCountyId") Integer subCountyId);

    @Query(value = "SELECT sln.id, sln.name, sln.geom, ST_DISTANCE(sln.geom, ST_SetSRID(ST_Point(:userLongitude, :userLattitude), 4326))" +
            "AS distance FROM store_location_nairobi sln ORDER BY " +
            "sln.geom <-> ST_SetSRID(ST_Point(:userLongitude, :userLattitude), 4326) LIMIT 5", nativeQuery = true)
    List<StoreLocation> findAllStoreByDistanceFromUser(@Param("userLongitude") Double userLongitude,
                                                               @Param("userLattitude") Double userLattitude);

}
