package com.wallet.wallet.handler.exeption;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
    
}
