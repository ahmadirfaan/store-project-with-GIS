package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.NairobiSubCounty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NairobiSubCountyRepository extends JpaRepository<NairobiSubCounty, Integer> {
}
