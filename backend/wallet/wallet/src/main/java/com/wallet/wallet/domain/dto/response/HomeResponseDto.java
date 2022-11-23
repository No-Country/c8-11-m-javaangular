package com.wallet.wallet.domain.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class HomeResponseDto {

    private Long currencyId;

    private Long lenguageId;

    private Double balanceExpense;

    private Double balanceIncome;

    private List<MoveResponseDto> moves;


}
