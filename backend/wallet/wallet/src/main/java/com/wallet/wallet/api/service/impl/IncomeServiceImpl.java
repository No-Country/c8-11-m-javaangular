package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.mapper.IncomeMapper;
import com.wallet.wallet.domain.model.Income;
import com.wallet.wallet.domain.repository.IIncomeRepository;

import lombok.AllArgsConstructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IncomeServiceImpl extends GenericServiceImpl<Income, IncomeResponseDto, IncomeRequestDto, Long> implements IIncomeService {

    private final IIncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper;


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
