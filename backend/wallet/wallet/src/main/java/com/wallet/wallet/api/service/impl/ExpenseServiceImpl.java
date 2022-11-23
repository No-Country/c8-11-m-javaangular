package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryGroupResponseDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.HomeResponseDto;
import com.wallet.wallet.domain.dto.response.MoveResponseDto;
import com.wallet.wallet.domain.mapper.ExpenseMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.mapper.IncomeMapper;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import com.wallet.wallet.domain.repository.IExpenseRepository;

import com.wallet.wallet.domain.repository.IIncomeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ExpenseServiceImpl extends GenericServiceImpl<Expense, ExpenseResponseDto, ExpenseRequestDto, Long> implements IExpenseService {

    private final ExpenseMapper expenseMapper;
    private final IExpenseRepository expenseRepository;

    private final IncomeMapper incomeMapper;
    private final IIncomeService incomeService;
    private final IIncomeRepository incomeRepository;

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

        List<Income> incomes = incomeRepository.getThreeByUserId(userId);
        incomes.forEach(income -> movesResponseDto.add(incomeMapper.entityToMoveResponseDto(income)));

        movesResponseDto.sort(Comparator.comparing(MoveResponseDto::getDate).reversed());
        while(movesResponseDto.size() > 3){
            movesResponseDto.remove(movesResponseDto.size()-1);
        }

        homeResponseDto.setBalanceExpense(getBalanceMonthlyByUserId(userId));
        homeResponseDto.setBalanceIncome(incomeService.getBalanceMonthlyByUserId(userId));

        homeResponseDto.setMoves(movesResponseDto);

        return homeResponseDto;
    }

    @Override
    public Map<String, Double> groupByCategoryByUserId(Long userId){
        List<ExpenseResponseDto> expenses = getAllByUserId(userId);
        Map<String, Double> categoryGroups = expenses.stream().collect(Collectors.groupingBy(ExpenseResponseDto::getCategoryName, Collectors.summingDouble(ExpenseResponseDto::getAmount)));
        return categoryGroups;
    }

    @Override
    public List<ExpenseResponseDto> filter(Long userId, List<Long> categoriesId, Double amountMin, Double amountMax, String orderBy, String order) {

        List<Expense> expenses = new ArrayList<>();

        if(categoriesId != null && amountMin != null && amountMax != null) {
            expenses = expenseRepository.filterByCategoriesAndAmount(userId, categoriesId, amountMin, amountMax, Sort.by(order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        } else if(categoriesId == null){
            expenses = expenseRepository.filterByAmount(userId, amountMin, amountMax, Sort.by(order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        } else {
            expenses = expenseRepository.filterByCategories(userId, categoriesId, Sort.by(order.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        }
            return expenseMapper.listEntityToListResponseDto(expenses);
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
