package com.wallet.wallet.domain.mapper;

import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements IMapper<Category, CategoryResponseDto, CategoryRequestDto> {

}
