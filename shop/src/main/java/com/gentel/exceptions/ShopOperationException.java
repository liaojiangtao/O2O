package com.gentel.exceptions;

public class ShopOperationException extends RuntimeException {
    private static final long serialVersionUID = -2214416321661199555L;

    public ShopOperationException(String msg){
        super(msg);
    }
}
