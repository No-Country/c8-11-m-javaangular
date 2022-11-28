package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.mapper.IncomeMapper;
import com.wallet.wallet.domain.model.EIncome;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.IIncomeRepository;

import com.wallet.wallet.domain.repository.IUserRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomeServiceImpl extends GenericServiceImpl<Income, IncomeResponseDto, IncomeRequestDto, Long> implements IIncomeService {

    private final IIncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper;

    private final IUserRepository userRepository;

    public IncomeResponseDto save(IncomeRequestDto incomeRequestDto) {
        return super.save(incomeRequestDto);
    }

    @Override
    public IncomeResponseDto findById(Long Id){
        return incomeMapper.entityToResponseDto(incomeRepository.findById(Id).get());
    }

    @Override
    public List<IncomeResponseDto> findAll(){

        List<IncomeResponseDto> incomeResponse = incomeRepository.findAll()
                .stream()
                .map(s -> incomeMapper.entityToResponseDto(s))
                .collect(Collectors.toList());

        return incomeResponse;
    }

    public Double getBalanceMonthlyByUserId(List<Income> incomes) {

        Double balance = 0.0;

        for (Income income : incomes){
            balance += income.getAmount();
        }

        return balance;
    }

    public Double getBalanceYearlyByUserId(Long userId, Integer year) {

        Double balance = 0.0;

        User user = userRepository.findById(userId).get();
        String userCodeCurrency = user.getCurrency().getCodeCurrency();
        Double userValueCurrency = user.getCurrency().getValueDollar();

        List<Income> incomesYearly = convertIncome(incomeRepository.getYearlyByUserId(userId, year),userCodeCurrency, userValueCurrency);
        for (Income income : incomesYearly){
            balance += (income.getAmount()/12);
        }

        return balance;
    }

    public List<Income> convertIncome(List<Income> incomes, String userCodeCurrency, Double userValueDollar){
        for(Income income : incomes){
            if(!income.getCurrency().getCodeCurrency().equals(userCodeCurrency)){
                if(income.getCurrency().getCodeCurrency().equals("USD")){
                    income.setAmount(income.getAmount()*income.getCurrency().getValueDollar());
                } else {
                    income.setAmount((income.getAmount()/income.getCurrency().getValueDollar()) * userValueDollar);
                }
            }
        }
        return incomes;
    }

    public void delete(Long id){
        super.delete(id);
    }

    @Override
    public JpaRepository<Income, Long> getRepository() {
        return incomeRepository;
    }

    @Override
    public IMapper<Income, IncomeResponseDto, IncomeRequestDto> getMapper() {
        return incomeMapper;
    }
}
