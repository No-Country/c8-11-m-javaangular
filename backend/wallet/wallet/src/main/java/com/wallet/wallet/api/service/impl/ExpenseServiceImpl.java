package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.*;
import com.wallet.wallet.domain.mapper.ExpenseMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.mapper.IncomeMapper;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.IExpenseRepository;

import com.wallet.wallet.domain.repository.IIncomeRepository;
import com.wallet.wallet.domain.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.net.PortUnreachableException;
import java.time.LocalDate;
import java.time.Month;
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

    private final IUserRepository userRepository;

    private final JwtUtil jwtUtil;

    public ExpenseResponseDto save(ExpenseRequestDto expenseRequestDto) {
        log.info("Gasto agregado correctamente");
        return super.save(expenseRequestDto);
    }

    @Override
    public List<ExpenseResponseDto> getAllByUserId(Long userId) {
        return expenseMapper.listEntityToListResponseDto(convertExpense(expenseRepository.getAllByUserId(userId)));
    }

    @Override
    public Double getBalanceMonthlyByUserId(Long userId, Integer month, Integer year) {
        //return expenseRepository.getBalanceMonthlyByUserId(userId, LocalDate.now().getMonthValue(););

        Double balance = 0.0;
        List<Expense> expenses = convertExpense(expenseRepository.getMonthlyByUserId(userId, month, year));
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
    public HomeResponseDto getForHome(String token){

        Long userId = jwtUtil.extractUserId(token.substring(7));
        User user = userRepository.findById(userId).get();

        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();

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

        homeResponseDto.setBalanceExpense(getBalanceMonthlyByUserId(userId, month, year));
        homeResponseDto.setBalanceIncome(incomeService.getBalanceMonthlyByUserId(userId, month, year));

        homeResponseDto.setMoves(movesResponseDto);
        homeResponseDto.setCurrencyId(user.getCurrency().getId());
        homeResponseDto.setFirstName(user.getFirstName());

        return homeResponseDto;
    }

    public StatisticsResponseDto getStatistics(String token){

        StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();

        Long userId = jwtUtil.extractUserId(token.substring(7));
        LocalDate now = LocalDate.now();
        Integer month = now.getMonthValue();
        Integer year = now.getYear();

        Double balanceYearly = incomeService.getBalanceYearlyByUserId(userId, now.getYear());

        List<Double> incomes = new ArrayList<>();
        List<Double> expenses = new ArrayList<>();
        List<Month> months = new ArrayList<>();
        Double balanceIncome = 0.0;
        Double balanceExpense = 0.0;

        if(now.getMonthValue() == 12){
            for(int i=0; i<12; i++){
                months.add(now.minusMonths(i).getMonth());
                balanceIncome = incomeService.getBalanceMonthlyByUserId(userId, month - i, year) + balanceYearly;
                incomes.add(balanceIncome);
                balanceExpense = getBalanceMonthlyByUserId(userId, month-i, year);
                expenses.add(balanceExpense);
            }
        } else {
            for(int i=0; i<12; i++){
                months.add(now.minusMonths(i).getMonth());
                year = now.minusMonths(i).getYear();
                month = now.minusMonths(i).getMonthValue();

                if(month== 12){
                    balanceYearly = incomeService.getBalanceYearlyByUserId(userId,  year);
                }

                balanceIncome = incomeService.getBalanceMonthlyByUserId(userId, month, year) + balanceYearly;
                incomes.add(balanceIncome);
                balanceExpense = getBalanceMonthlyByUserId(userId, month, year);
                expenses.add(balanceExpense);
            }
        }
        statisticsResponseDto.setIncomes(incomes);
        statisticsResponseDto.setExpenses(expenses);
        statisticsResponseDto.setMonths(months);
        return statisticsResponseDto;
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
