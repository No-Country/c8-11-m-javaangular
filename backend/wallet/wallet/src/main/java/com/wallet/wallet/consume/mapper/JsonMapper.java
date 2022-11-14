package com.wallet.wallet.consume.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public record JsonMapper(ObjectMapper mapper) {

    public <S, D> D map(String json, Class<D> destinationClass)
            throws JsonMappingException, JsonProcessingException {
        return mapper.readValue(json, destinationClass);
    }
}
