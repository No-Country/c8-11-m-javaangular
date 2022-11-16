package com.wallet.wallet.api.service;

import com.wallet.wallet.api.service.generic.GenericServiceAPI;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.model.Category;

public interface ICategoryService extends GenericServiceAPI<Category, CategoryResponseDto, CategoryRequestDto, Long> {
}
