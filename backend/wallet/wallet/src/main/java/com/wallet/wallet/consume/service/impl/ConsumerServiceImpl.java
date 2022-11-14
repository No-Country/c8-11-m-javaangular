package com.wallet.wallet.consume.service.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.wallet.wallet.consume.dto.CurrencyDto;
import com.wallet.wallet.consume.fixer.ApiFixer;
import com.wallet.wallet.consume.mapper.JsonMapper;
import com.wallet.wallet.consume.service.IConsumer;

@Service
public record ConsumerServiceImpl(ApiFixer api, JsonMapper mapper) implements IConsumer {

    public CurrencyDto getCurrency() {
        String json = api.getConnection(ENDPOINT_CURRENCIES);
        CurrencyDto dto = null;        
        JSONObject jsonObject = new JSONObject(json);

        try {
            jsonObject.getJSONObject("rates");
            dto = mapper.map(jsonObject.toString(), CurrencyDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }        
        return dto;
    }
}
