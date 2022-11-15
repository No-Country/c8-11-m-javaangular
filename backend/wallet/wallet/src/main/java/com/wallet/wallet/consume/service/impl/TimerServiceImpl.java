package com.wallet.wallet.consume.service.impl;

import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wallet.wallet.consume.service.IConsumer;
import com.wallet.wallet.consume.service.ITimer;
import com.wallet.wallet.domain.model.Currency;

@Service
@EnableScheduling
public record TimerServiceImpl(IConsumer consumerService) implements ITimer{
    /*
     * CRON expression, tiene anotaciones por defecto pero se pueden usar expreciones CRON para ser mas específico.
     * por ejemplo: 1 hora = "0 0 * ? * *" / 1 segundo = "* * * ? * *"
     * 
     * @Scheduled le dice a java que este método se tiene que ejecutar cada vez que se cumpla el tiempo especificado.
     */
    //@Scheduled(cron = "@hourly")
    @Scheduled(cron = "0/5 * * * * ?") // -> ejemplo de cada 5 segundos
    public void refreshCurrency() {
        List<Currency> currencies = consumerService.getCurrencies();

        //System.out.println(dto);
        System.out.println("updated...");
    }
    
}
