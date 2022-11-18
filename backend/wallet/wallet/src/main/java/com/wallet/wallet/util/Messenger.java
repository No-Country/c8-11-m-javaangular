package com.wallet.wallet.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.wallet.wallet.domain.enums.EMessageCode;

@Component
public record Messenger(MessageSource messenger) {
    
    public String getMessage(EMessageCode code){
        return messenger.getMessage(code.name(), null, Locale.getDefault());
    }
}
