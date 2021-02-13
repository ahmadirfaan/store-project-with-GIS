package com.irfaan.api.inventory.models;

public class DetailTransactionElement {

    private Integer id;

    private Integer quantity;

    private ItemElement item;

    private Integer totalPrice;

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

    public ItemElement getItem() {
        return item;
    }

    public void setItem(ItemElement item) {
        this.item = item;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
