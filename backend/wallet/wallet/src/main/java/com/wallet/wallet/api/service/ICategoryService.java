package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.model.Category;

import java.util.List;

public interface ICategoryService extends GenericServiceAPI<Category, CategoryResponseDto, CategoryRequestDto, Long> {

    CategoryResponseDto update(CategoryUpdateDto categoryUpdateDto, Long id);

    CategoryResponseDto getById(Long Id);

    List<CategoryResponseDto> getAll();

    List<CategoryResponseDto> getAllByUserId(Long userId);

}
