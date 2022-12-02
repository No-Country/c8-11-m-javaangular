package com.wallet.wallet.handler.exeption;

public class ApiRateLimitException extends RuntimeException{
    
    public ApiRateLimitException(String message){
        super(message);
    }
}
