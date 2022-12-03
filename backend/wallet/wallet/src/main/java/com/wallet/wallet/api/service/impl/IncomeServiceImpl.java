package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.request.ExpenseUpdateDto;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.request.IncomeUpdateDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.mapper.IncomeMapper;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.IIncomeRepository;

import com.wallet.wallet.handler.exeption.UserUnauthorizedException;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wallet.wallet.domain.enums.EMessageCode.USER_UNAUTHORIZED;

@Service
@AllArgsConstructor
public class IncomeServiceImpl extends GenericServiceImpl<Income, IncomeResponseDto, IncomeRequestDto, Long> implements IIncomeService {

    private final IIncomeRepository repository;
    private final IncomeMapper mapper;
    private final IUserService userService;

    private final JwtUtil jwtUtil;

    private final MessageSource messenger;

    @Override
    public IncomeResponseDto save(IncomeRequestDto incomeRequestDto, String token) {
        Long userId = jwtUtil.extractUserId(token);
        incomeRequestDto.setUserId(userId);
        return super.save(incomeRequestDto);
    }

    @Override
    public IncomeResponseDto update(IncomeUpdateDto incomeUpdateDto, Long id, String token) {

        Long userId = jwtUtil.extractUserId(token);

        Optional<Income> income = repository.findById(id);

        if (userId.equals(income.get().getUser().getId())) {
            incomeUpdateDto.setId(id);
            Income incomeSave = mapper.updateToEntity(incomeUpdateDto);
            incomeSave.setCurrency(income.get().getCurrency());
            incomeSave.setUser(income.get().getUser());
            repository.save(incomeSave);
        } else {
            throw new UserUnauthorizedException(messenger.getMessage(USER_UNAUTHORIZED.name(),
                    new Object[] {userId, income.get().getUser().getId()}, Locale.getDefault()));
        }
        return getById(id);
    }

    @Override
    public IncomeResponseDto findById(Long Id){
        return mapper.entityToResponseDto(repository.findById(Id).get());
    }

    @Override
    public List<IncomeResponseDto> findAll(){

        List<IncomeResponseDto> incomeResponse = repository.findAll()
                .stream()
                .map(s -> mapper.entityToResponseDto(s))
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

        User user = userService.getById(userId);
        String userCodeCurrency = user.getCurrency().getCodeCurrency();
        Double userValueCurrency = user.getCurrency().getValueDollar();

        List<Income> incomesYearly = convertIncome(repository.getYearlyByUserId(userId, year),userCodeCurrency, userValueCurrency);
        for (Income income : incomesYearly){
            balance += (income.getAmount()/12);
        }

        return balance;
    }

    //genérico
    public Double formatDecimals(Double number, Integer decimals) {
        return Math.round(number * Math.pow(10, decimals)) / Math.pow(10, decimals);
    }

    public List<Income> convertIncome(List<Income> incomes, String userCodeCurrency, Double userValueDollar){
        for(Income income : incomes){
            if(!income.getCurrency().getCodeCurrency().equals(userCodeCurrency)){
                if(income.getCurrency().getCodeCurrency().equals("USD")){
                    income.setAmount(formatDecimals(income.getAmount()*income.getCurrency().getValueDollar(),2));
                } else {
                    income.setAmount(formatDecimals((income.getAmount()/income.getCurrency().getValueDollar()) * userValueDollar,2));
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
        return repository;
    }

    @Override
    public IMapper<Income, IncomeResponseDto, IncomeRequestDto> getMapper() {
        return mapper;
    }

    @Override
    public MessageSource getMessenger() {
        return null;
    }
}
