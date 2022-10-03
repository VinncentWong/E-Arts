package com.demo.exception;

public class FavoriteArtworkNotFoundException extends Exception{

    public FavoriteArtworkNotFoundException() {
    }

    public FavoriteArtworkNotFoundException(String message) {
        super(message);
    }
    
}
