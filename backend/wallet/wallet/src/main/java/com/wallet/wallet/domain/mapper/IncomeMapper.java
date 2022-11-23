package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.IncomeResponseDto;
import com.wallet.wallet.domain.dto.response.MoveResponseDto;
import com.wallet.wallet.domain.model.Expense;
import com.wallet.wallet.domain.model.Income;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper implements IMapper<Income, IncomeResponseDto, IncomeRequestDto>{

    //@Mapping(source = "category.name", target = "categoryName")
    //@Mapping(source = "category.icon", target = "categoryIcon")
    //@Mapping(source = "category.colorCode", target = "categoryColorCode")
    @Mapping(source = "currency.codeCurrency", target = "codeCurrency")
    public abstract IncomeResponseDto entityToResponseDto(Income income);

    //@Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "currencyId", target = "currency.id")
    @Mapping(source = "userId", target = "user.id")
    public abstract Income requestDtoToEntity(IncomeRequestDto incomeRequestDto);

    //@Mapping(source = "category.name", target = "categoryName")
    //@Mapping(source = "category.icon", target = "categoryIcon")
    //@Mapping(source = "category.colorCode", target = "categoryColorCode")
    @Mapping(source = "currency.codeCurrency", target = "codeCurrency")
    public abstract MoveResponseDto entityToMoveResponseDto(Income income);

}
