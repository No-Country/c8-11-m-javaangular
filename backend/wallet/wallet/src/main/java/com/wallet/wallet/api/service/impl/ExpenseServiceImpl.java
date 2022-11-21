package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryGroupResponseDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.HomeResponseDto;
import com.wallet.wallet.domain.dto.response.MoveResponseDto;
import com.wallet.wallet.domain.mapper.ExpenseMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.repository.IExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ExpenseServiceImpl extends GenericServiceImpl<Expense, ExpenseResponseDto, ExpenseRequestDto, Long> implements IExpenseService {

    private final ExpenseMapper expenseMapper;

    private final IExpenseRepository expenseRepository;

    public ExpenseResponseDto save(ExpenseRequestDto expenseRequestDto) {
        log.info("Gasto agregado correctamente");
        return super.save(expenseRequestDto);
    }

    @Override
    public List<ExpenseResponseDto> getAllByUserId(Long userId) {
        return expenseMapper.listEntityToListResponseDto(convertExpense(expenseRepository.getAllByUserId(userId)));
    }

    @Override
    public Double getBalanceMonthlyByUserId(Long userId) {
        //return expenseRepository.getBalanceMonthlyByUserId(userId, LocalDate.now().getMonthValue(););

        Double balance = 0.0;
        List<Expense> expenses = convertExpense(expenseRepository.getMonthlyByUserId(userId, LocalDate.now().getMonthValue()));
        for (Expense expense : expenses){
            balance += expense.getAmount();
        }
        return balance;
    }

    @Override
    public Double getBalanceSpecificByUserId(Long userId, LocalDate start, LocalDate end) {
        //return expenseRepository.getBalanceSpecificByUserId(userId, start, end);

        Double balance = 0.0;
        List<Expense> expenses = convertExpense(expenseRepository.getSpecificByUserId(userId, start, end));
        for (Expense expense : expenses){
            balance += expense.getAmount();
        }
        return balance;
    }

    @Override
    public HomeResponseDto getForHome(Long userId){

        HomeResponseDto homeResponseDto = new HomeResponseDto();

        List<MoveResponseDto> movesResponseDto = new ArrayList<>();
        List<Expense> expenses = expenseRepository.getThreeByUserId(userId);
        expenses.forEach(expense -> movesResponseDto.add(expenseMapper.entityToMoveResponseDto(expense)));

        homeResponseDto.setBalanceExpense(getBalanceMonthlyByUserId(userId));
        //homeResponseDto.setBalanceIncome(incomeRepository.getIncomeMonthlyByUserId(userId, monthNow));

        homeResponseDto.setMoves(movesResponseDto);

        return homeResponseDto;
    }

    @Override
    public Map<String, Double> groupByCategoryByUserId(Long userId){
        List<ExpenseResponseDto> expenses = getAllByUserId(userId);
        Map<String, Double> categoryGroups = expenses.stream().collect(Collectors.groupingBy(ExpenseResponseDto::getCategoryName, Collectors.summingDouble(ExpenseResponseDto::getAmount)));
        return categoryGroups;
    }

    public void delete(Long id){
        log.info("Gasto eliminado correctamente");
        super.delete(id);
    }

    // CONVERTER
    public List<Expense> convertExpense(List<Expense> expenses){

        //reemplazar por información del usuario
        String userCodeCurrency = "ARS";
        Double userValueDollar = 163.20;

        for(Expense expense : expenses){
            if(!expense.getCurrency().getCodeCurrency().equals(userCodeCurrency)){
                if(expense.getCurrency().getCodeCurrency().equals("USD")){
                    expense.setAmount(expense.getAmount()*expense.getCurrency().getValueDollar());
                } else {
                    expense.setAmount((expense.getAmount()/expense.getCurrency().getValueDollar()) * userValueDollar);
                }
            }
        }
        return expenses;
    }

    @Override
    public JpaRepository<Expense, Long> getRepository() {
        return expenseRepository;
    }

    @Override
    public IMapper<Expense, ExpenseResponseDto, ExpenseRequestDto> getMapper() {
        return expenseMapper;
    }

}
