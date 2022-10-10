package com.demo.exception;

public class SuperAdminNotFoundException extends Exception{

    public SuperAdminNotFoundException() {
    }

    public SuperAdminNotFoundException(String message) {
        super(message);
    }
    
}
