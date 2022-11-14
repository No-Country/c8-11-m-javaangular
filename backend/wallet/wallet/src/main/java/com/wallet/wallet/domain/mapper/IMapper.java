package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class IMapper {

    //@Mapping(target = "", source = "")
    public abstract Income toIncomeResponseDto(IncomeResponseDto incomeResponseDto);
}
