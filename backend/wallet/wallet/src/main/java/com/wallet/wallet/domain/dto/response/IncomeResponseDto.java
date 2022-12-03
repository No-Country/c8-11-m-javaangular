package com.wallet.wallet.domain.dto.response;

import com.wallet.wallet.domain.enums.EIncome;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeResponseDto {

    private Long id;

    private Double amount;

    private String description;

    private static String categoryName = "Ingreso";

    private static String categoryIcon = "income";

    private static String categoryColorCode = "#F239J3";

    private String codeCurrency;

    private LocalDate date;

    private Boolean isIncluded;

    private EIncome type;

    private Boolean deleted;
}
