package com.wallet.wallet.consume.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.wallet.consume.connection.ApiFixer;
import com.wallet.wallet.consume.dto.CurrencyDto;
import com.wallet.wallet.consume.service.IConsumer;
import com.wallet.wallet.domain.model.Currency;

@Service
public record ConsumerServiceImpl(ApiFixer api) implements IConsumer {

    public List<Currency> getCurrencies() {
        // String jsonString = api.getConnection(ENDPOINT_CURRENCIES);
        // JSONObject jsonMain = new JSONObject(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        List<Currency> currencies = new ArrayList<>();

        try {
            Object object = new JSONObject(api.getConnection(ENDPOINT_CURRENCIES)).getJSONObject("rates");
            Map<String, Double> map = mapper.readValue(object.toString(), new TypeReference<Map<String, Double>>() {
            });

            List<String> keys = getListOfKeys(map);
            List<Double> values = getListOfValues(map);

            for (int i = 0; i < keys.size(); i++) {
                currencies.add(Currency.builder()
                        .codeCurrency(keys.get(i))
                        .valueDollar(values.get(i))
                        .build());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return currencies;
    }
}
