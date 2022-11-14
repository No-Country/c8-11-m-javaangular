package com.wallet.wallet.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    public static ResponseEntity<Object> responseBuilder(HttpStatus httpStatus, String path, Object object){
        Map<String, Object> response = new HashMap<>();
        response.put("status", httpStatus);
        response.put("path", path);
        //response.put((object instanceof exception ? "response" : "error"), object);

        return ResponseEntity.status(httpStatus).body(response);
    };
}
