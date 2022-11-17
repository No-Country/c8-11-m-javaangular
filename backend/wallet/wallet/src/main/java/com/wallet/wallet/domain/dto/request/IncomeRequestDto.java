package com.wallet.wallet.domain.dto.request;

import com.wallet.wallet.domain.model.Currency;
import com.wallet.wallet.domain.model.EIncome;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeRequestDto {

    private Long id;

    private Double amount;

    private String description;

    private Currency currency;

    private LocalDate date;

    private Boolean isIncluded;

    private EIncome type;

    private Boolean deleted;
}
