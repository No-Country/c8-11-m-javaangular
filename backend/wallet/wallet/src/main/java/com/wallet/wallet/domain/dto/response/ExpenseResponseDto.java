package com.wallet.wallet.domain.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseResponseDto {

    private Long id;

    private Double amount;

    private String description;

    private String categoryName;

    private String categoryIcon;

    private String codeCurrency;

    private LocalDate date;

    private Boolean isIncluded;

    private Boolean deleted = Boolean.FALSE;

}
