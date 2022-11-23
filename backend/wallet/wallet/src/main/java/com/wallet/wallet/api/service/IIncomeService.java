package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.model.Income;

import java.util.List;

public interface IIncomeService extends GenericServiceAPI<Income, IncomeResponseDto, IncomeRequestDto, Long> {

    IncomeResponseDto findById(Long Id);

    List<IncomeResponseDto> findAll();

    Double getBalanceMonthlyByUserId(Long userId);
}
