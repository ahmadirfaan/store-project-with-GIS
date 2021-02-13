package com.irfaan.api.inventory.models;

import javax.validation.constraints.NotNull;

public class DetailTransactionRequest {

    private Integer id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer itemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

}
