package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.model.Expense;

import java.util.Optional;

public interface IExpenseService extends GenericServiceAPI<Expense, ExpenseResponseDto, ExpenseRequestDto, Long> {

    ExpenseResponseDto findOne(Long id);

}
