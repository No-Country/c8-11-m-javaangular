package com.wallet.wallet.api.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wallet.wallet.api.service.ICurrencyService;
import com.wallet.wallet.consume.dto.CurrencyDto;

@Service
public class CurrencyServiceImpl implements ICurrencyService{

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(CurrencyDto dto) {
        
        //Map<String, Double> map = (k, v) -> {

       
        
    }
    

}
