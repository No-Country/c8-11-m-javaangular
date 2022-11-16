package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.mapper.CategoryMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Category;
import com.wallet.wallet.domain.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryResponseDto, CategoryRequestDto, Long> implements ICategoryService {

    private final CategoryMapper categoryMapper;

    private final ICategoryRepository categoryRepository;

    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {

        //String jwt = req...

        //if (category.getName().isEmpty()) {
        //    category.setIsDefault(true);
        //} else {
        //    category.setIsDefault(false);
        //}

        //category.setUserIdCreate(jwt.extractUserId);

        log.info("Categoría agregada correctamente");
        return super.save(categoryRequestDto);
    }

    public void delete(Long id){
        log.info("Categoría eliminada correctamente");
        super.delete(id);
    }

    @Override
    public JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }

    @Override
    public IMapper<Category, CategoryResponseDto, CategoryRequestDto> getMapper() {
        return categoryMapper;
    }
}
