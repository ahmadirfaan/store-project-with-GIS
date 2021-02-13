package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
