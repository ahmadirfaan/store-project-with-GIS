package com.irfaan.api.inventory.models;

import java.time.LocalDateTime;

public class TransactionSearch extends PageSearch {

    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
