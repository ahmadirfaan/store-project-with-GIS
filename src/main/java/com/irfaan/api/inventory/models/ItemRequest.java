package com.irfaan.api.inventory.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemRequest {


    @Size(min = 1,max = 100)
    private String name; //Harus menggunakan validasi agar lebih ciamik cihuyy.....

    @NotNull
    private Integer unitId;

    private Integer price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
