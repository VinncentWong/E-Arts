package com.demo.exception;

public class AddressNotFoundException extends Exception{

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }
    
}
