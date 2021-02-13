package com.irfaan.api.inventory.services.impl;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.repositories.ItemRepository;
import com.irfaan.api.inventory.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends CommonServiceImpl<Item,Integer> implements ItemService {


//    @Autowired
//    private UnitService unitService;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }

    // @Transactional // Digunakan lebih dari 1 proses pada query dan ingin jika satu gagal maka semua gagal maka ditambah annotasi transactional
//    @Override
//    public Item addWithUnit(Item entity) {
//        Unit unit = unitService.save(entity.getUnit());
//        entity.setUnit(unit);
//
//        return repository.save(entity);
//    }

}
