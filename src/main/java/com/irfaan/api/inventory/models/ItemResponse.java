package com.irfaan.api.inventory.models;

import javax.validation.constraints.Size;

public class ItemResponse {

    private Integer id;

    @Size(min = 1,max = 100)
    private String name; //Harus menggunakan validasi agar lebih ciamik cihuyy.....
    private UnitModel unit;
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public void setUnit(UnitModel unit) {
        this.unit = unit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
