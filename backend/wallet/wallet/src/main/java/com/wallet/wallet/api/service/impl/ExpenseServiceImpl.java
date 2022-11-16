package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.mapper.ExpenseMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.repository.IExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
    public ExpenseResponseDto findOne(Long id) {
        return expenseMapper.entityToResponseDto(expenseRepository.findById(id).get());
    }

    public void delete(Long id){
        log.info("Gasto eliminado correctamente");
        super.delete(id);
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
