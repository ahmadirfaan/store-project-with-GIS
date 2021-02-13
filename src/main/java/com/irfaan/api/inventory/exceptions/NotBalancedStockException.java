package com.irfaan.api.inventory.exceptions;

import org.springframework.http.HttpStatus;

public class NotBalancedStockException extends ApplicationException {

    public NotBalancedStockException() {
        super("error." + HttpStatus.NOT_FOUND.value() + ".NotBalancedStock", HttpStatus.NOT_FOUND);
    }

}
