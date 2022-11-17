package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.model.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper implements IMapper<Income, IncomeResponseDto, IncomeRequestDto>{

}
