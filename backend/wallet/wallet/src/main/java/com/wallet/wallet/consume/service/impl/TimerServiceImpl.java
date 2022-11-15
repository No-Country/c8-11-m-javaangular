package com.wallet.wallet.consume.service.impl;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wallet.wallet.api.service.ICurrencyService;
import com.wallet.wallet.consume.dto.CurrencyDto;
import com.wallet.wallet.consume.service.IConsumer;
import com.wallet.wallet.consume.service.ITimer;

@Service
@EnableScheduling
public record TimerServiceImpl(IConsumer consumerService, ICurrencyService currencyService) implements ITimer{
    /*
     * CRON expression, tiene anotaciones por defecto pero se pueden usar expreciones CRON para ser mas específico.
     * por ejemplo: 1 hora = "0 0 * ? * *" / 1 segundo = "* * * ? * *"
     * 
     * @Scheduled le dice a java que este método se tiene que ejecutar cada vez que se cumpla el tiempo especificado.
     */
    //@Scheduled(cron = "@hourly")
    @Scheduled(cron = "0/25 * * * * ?") // -> ejemplo de cada 5 segundos
    public void refreshCurrency() {
        List<CurrencyDto> currencies = consumerService.getCurrencies();
        currencyService.updateAll(currencies);
        System.out.println("updated...");
    }
    
}
