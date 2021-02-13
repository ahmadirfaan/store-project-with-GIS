package com.irfaan.api.inventory.models;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionRequest {

    private Integer id;

    private LocalDateTime dateTransaction;

    private List<DetailTransactionRequest>  detailTransactionList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public List<DetailTransactionRequest> getDetailTransactionList() {
        return detailTransactionList;
    }

    public void setDetailTransactionList(List<DetailTransactionRequest> detailTransactionList) {
        this.detailTransactionList = detailTransactionList;
    }
}
