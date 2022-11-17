package com.wallet.wallet.domain.dto.response;

import com.wallet.wallet.domain.model.Currency;
import com.wallet.wallet.domain.model.EIncome;

import java.time.LocalDate;

public class IncomeResponseDto {

    private Long id;

    private Double amount;

    private String description;

    private Currency currency;

    private LocalDate date;

    private Boolean isIncluded;

    private EIncome type;

    private Boolean deleted;
}
