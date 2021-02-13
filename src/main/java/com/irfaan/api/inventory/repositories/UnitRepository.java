package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}
