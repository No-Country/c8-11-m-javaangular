package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.HomeResponseDto;
import com.wallet.wallet.domain.dto.response.MoveResponseDto;
import com.wallet.wallet.domain.model.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ExpenseMapper implements IMapper<Expense, ExpenseResponseDto, ExpenseRequestDto> {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.icon", target = "categoryIcon")
    @Mapping(source = "category.colorCode", target = "categoryColorCode")
    @Mapping(source = "currency.codeCurrency", target = "codeCurrency")
    public abstract ExpenseResponseDto entityToResponseDto(Expense expense);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "currencyId", target = "currency.id")
    @Mapping(source = "userId", target = "user.id")
    public abstract Expense requestDtoToEntity(ExpenseRequestDto expenseRequestDto);

    public abstract List<ExpenseResponseDto> listEntityToListResponseDto(List<Expense> expenses);

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "category.icon", target = "categoryIcon")
    @Mapping(source = "category.colorCode", target = "categoryColorCode")
    @Mapping(source = "currency.codeCurrency", target = "codeCurrency")
    public abstract MoveResponseDto entityToMoveResponseDto(Expense expense);

}
