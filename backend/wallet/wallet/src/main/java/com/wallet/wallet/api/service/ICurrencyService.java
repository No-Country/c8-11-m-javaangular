package com.wallet.wallet.api.service;

import java.util.List;

import com.wallet.wallet.consume.dto.CurrencyDto;
public interface ICurrencyService {
    
    void updateAll(List<CurrencyDto> currencies);
}
