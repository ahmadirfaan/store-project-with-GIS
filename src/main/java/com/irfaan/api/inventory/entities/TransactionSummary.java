package com.irfaan.api.inventory.entities;

import java.time.LocalDateTime;

public class TransactionSummary {

    private Integer transactionId;
    private Integer itemId;
    private Integer quantity;
    private Long totalPriceTransaction;
    private LocalDateTime dateTransaction;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPriceTransaction() {
        return totalPriceTransaction;
    }

    public void setTotalPriceTransaction(Long totalPriceTransaction) {
        this.totalPriceTransaction = totalPriceTransaction;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}
