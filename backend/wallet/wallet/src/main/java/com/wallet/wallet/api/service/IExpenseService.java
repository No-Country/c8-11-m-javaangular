package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryGroupResponseDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.HomeResponseDto;
import com.wallet.wallet.domain.dto.response.StatisticsResponseDto;
import com.wallet.wallet.domain.model.Expense;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IExpenseService extends GenericServiceAPI<Expense, ExpenseResponseDto, ExpenseRequestDto, Long> {

    List<ExpenseResponseDto> getAllByUserId(Long userId);

    Double getBalanceMonthlyByUserId(Long userId, Integer month, Integer year);

    Double getBalanceSpecificByUserId(Long userId, LocalDate start, LocalDate end);

    Map<String, Double> groupByCategoryByUserId(Long userId);

    HomeResponseDto getForHome(String token);

    List<ExpenseResponseDto> filter(Long userId, List<Long> categoriesId, Double amountMax, Double amountMin, String orderBy, String order);

    StatisticsResponseDto getStatistics(String token);
}
