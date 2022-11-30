package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.IUserService;
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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl extends GenericServiceImpl<Expense, ExpenseResponseDto, ExpenseRequestDto, Long> implements IExpenseService {

    private final ExpenseMapper expenseMapper;
    private final IExpenseRepository expenseRepository;

    private final IncomeMapper incomeMapper;
    private final IIncomeService incomeService;
    private final IIncomeRepository incomeRepository;

    private final IUserService userService;

    private final JwtUtil jwtUtil;

    public ExpenseResponseDto save(ExpenseRequestDto expenseRequestDto, String token) {
        Long userId = jwtUtil.extractUserId(token);
        expenseRequestDto.setUserId(userId);

        return super.save(expenseRequestDto);
    }

    @Override
    public List<ExpenseResponseDto> getAllByUserId(String token) {
        Long userId = jwtUtil.extractUserId(token);
        User user = userService.getById(userId);
        String userCodeCurrency = user.getCurrency().getCodeCurrency();
        Double userValueCurrency = user.getCurrency().getValueDollar();
        return expenseMapper.listEntityToListResponseDto(convertExpense(expenseRepository.getAllByUserId(userId), userCodeCurrency, userValueCurrency));
    }



    /*
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
    */

    @Override
    public Double getBalanceMonthlyByUserId(List<Expense> expenses) {

        Double balance = 0.0;
        for (Expense expense : expenses){
            balance += expense.getAmount();
        }
        return balance;
    }

    @Override
    public Double getBalanceSpecificByUserId(Long userId, LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public HomeResponseDto getForHome(String token){

        Long userId = jwtUtil.extractUserId(token.substring(7));
        User user = userService.getById(userId);
        String userCodeCurrency = user.getCurrency().getCodeCurrency();
        Double userValueCurrency = user.getCurrency().getValueDollar();

        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();

        List<Expense> expenses = convertExpense(expenseRepository.getMonthlyByUserId(userId, month, year), userCodeCurrency, userValueCurrency);
        List<Income> incomes = incomeService.convertIncome(incomeRepository.getMonthlyByUserIdForHome(userId, month, year), userCodeCurrency, userValueCurrency);

        HomeResponseDto homeResponseDto = new HomeResponseDto();

        List<MoveResponseDto> movesResponseDto = new ArrayList<>();
        for(int i=0; i<3; i++){
            movesResponseDto.add(expenseMapper.entityToMoveResponseDto(expenses.get(i)));
        }

        for(int i=0; i<3; i++){
            movesResponseDto.add(incomeMapper.entityToMoveResponseDto(incomes.get(i)));
        }

        movesResponseDto.sort(Comparator.comparing(MoveResponseDto::getDate).reversed());
        while(movesResponseDto.size() > 3){
            movesResponseDto.remove(movesResponseDto.size()-1);
        }

        homeResponseDto.setBalanceExpense(getBalanceMonthlyByUserId(expenses));
        homeResponseDto.setBalanceIncome(incomeService.getBalanceMonthlyByUserId(incomes));

        homeResponseDto.setFirstName(user.getFirstName());
        homeResponseDto.setMonthNow(LocalDate.now().getMonth().toString().toLowerCase());
        homeResponseDto.setMoves(movesResponseDto);

        return homeResponseDto;
    }

    public StatisticsResponseDto getStatistics(String token){

        StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();

        Long userId = jwtUtil.extractUserId(token.substring(7));
        User user = userService.getById(userId);
        String userCodeCurrency = user.getCurrency().getCodeCurrency();
        Double userValueCurrency = user.getCurrency().getValueDollar();

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

                List<Income> incomeConvert = incomeService.convertIncome(incomeRepository.getMonthlyByUserId(userId, month-i, year), userCodeCurrency, userValueCurrency);
                balanceIncome = incomeService.getBalanceMonthlyByUserId(incomeConvert);
                incomes.add(balanceIncome);

                List<Expense> expensesConvert = convertExpense(expenseRepository.getMonthlyByUserId(userId, month-i, year), userCodeCurrency, userValueCurrency);
                balanceExpense = getBalanceMonthlyByUserId(expensesConvert);
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
                List<Income> incomeConvert = incomeService.convertIncome(incomeRepository.getMonthlyByUserId(userId, month, year), userCodeCurrency, userValueCurrency);
                balanceIncome = incomeService.getBalanceMonthlyByUserId(incomeConvert) + balanceYearly;
                incomes.add(balanceIncome);
                List<Expense> expensesConvert = convertExpense(expenseRepository.getMonthlyByUserId(userId, month, year), userCodeCurrency, userValueCurrency);
                balanceExpense = getBalanceMonthlyByUserId(expensesConvert);
                expenses.add(balanceExpense);
            }
        }
        statisticsResponseDto.setIncomes(incomes);
        statisticsResponseDto.setExpenses(expenses);
        statisticsResponseDto.setMonths(months);
        return statisticsResponseDto;
    }

    @Override
    public Map<String, Double> groupByCategoryByUserId(String token){
        List<ExpenseResponseDto> expenses = getAllByUserId(token);
        Map<String, Double> categoryGroups = expenses.stream().collect(Collectors.groupingBy(ExpenseResponseDto::getCategoryName, Collectors.summingDouble(ExpenseResponseDto::getAmount)));
        return categoryGroups;
    }

    @Override
    public List<ExpenseResponseDto> filter(String token, List<Long> categoriesId, Double amountMin, Double amountMax, String orderBy, String order) {

        Long userId = jwtUtil.extractUserId(token.substring(7));

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

    public void delete(Long id, String token){
        //mover lógica al generic, agregar excepciones
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense.get().getUser().getId() == id){
            super.delete(id);
        }
    }

    public List<Expense> convertExpense(List<Expense> expenses, String userCodeCurrency, Double userValueDollar){
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
