package com.wallet.wallet.consume.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wallet.wallet.consume.dto.CurrencyDto;

public interface IConsumer {
    
    final String ENDPOINT_CURRENCIES = "latest?symbols=ARS%2CBOB%2CBRL%2CCLP%2CCOP%2CPEN%2CPYG%2CVEF%2CMXN%2CEUR&base=USD";

    CurrencyDto getCurrency() throws JsonMappingException, JsonProcessingException;
}
