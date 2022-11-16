package com.wallet.wallet.domain.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExpenseRequestDto {

    private Long id;

    private Double amount;

    private String description;

    private Long categoryId;

    private Long currencyId;

    private LocalDate date;

    private Boolean isIncluded;

    private Boolean deleted = Boolean.FALSE;
}
