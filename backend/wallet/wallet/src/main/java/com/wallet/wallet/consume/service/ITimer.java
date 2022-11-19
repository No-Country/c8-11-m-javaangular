package com.wallet.wallet.consume.service;

public interface ITimer {

    final String CRON_HOURS = "6/12 ?";
    
    void refreshCurrency();
}
