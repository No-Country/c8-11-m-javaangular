package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.mapper.CategoryMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Category;
import com.wallet.wallet.domain.repository.ICategoryRepository;
import com.wallet.wallet.handler.exeption.ExampleException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryResponseDto, CategoryRequestDto, Long> implements ICategoryService {

    private final CategoryMapper categoryMapper;

    private final ICategoryRepository categoryRepository;

    //agregar lógica usuario
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

    //agregar lógica usuario
    @Override
    public CategoryResponseDto update(CategoryUpdateDto categoryUpdateDto, Long id){
        categoryUpdateDto.setId(id);
        categoryRepository.save(categoryMapper.updateToEntity(categoryUpdateDto));
        log.info("Categoría actualizada correctamente");
        return getById(id);
    }

    //agregar excepción
    @Override
    public CategoryResponseDto getById(Long Id){
        return categoryMapper.entityToResponseDto(categoryRepository.findById(Id).orElseThrow(() -> new ExampleException("")));
    }

    @Override
    public List<CategoryResponseDto> getAll(){
        return categoryMapper.listEntityToListResponseDto(categoryRepository.findAll());
    }

    //agregar lógica usuario en el controller
    @Override
    public List<CategoryResponseDto> getAllByUserId(Long userId){
        return categoryMapper.listEntityToListResponseDto(categoryRepository.getAllByUserId(userId));
    }

    //agregar excepción en el generic
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
