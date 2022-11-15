package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.consume.dto.CurrencyDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.model.Currency;
import com.wallet.wallet.domain.model.Income;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class IMapper {

    //@Mapping(target = "", source = "")
    public abstract Income toIncomeResponseDto(IncomeResponseDto incomeResponseDto);

    @Mapping(target = "valueDollar", source = "valueDollar")
    public abstract List<Currency> toCurrencyList(List<CurrencyDto> dtos);
}
