package com.wallet.wallet.domain.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MoveResponseDto {

    private Double amount;

    private String categoryName;

    private String categoryIcon;

    private String categoryColorCode;

    private String codeCurrency;

    private LocalDate date;

    private Boolean isIncluded;

}
