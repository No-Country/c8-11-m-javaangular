package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.mapper.CategoryMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Category;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.ICategoryRepository;
import com.wallet.wallet.domain.repository.IUserRepository;
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

    private final IUserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto, String token) {

        Long id = jwtUtil.extractUserId(token.substring(7));
        User user = userRepository.findById(id).get();

        categoryRequestDto.setUserIdCreate(id);

        if(user.getRole().getName().toString().equals("ADMIN")){
            categoryRequestDto.setIsDefault(true);
        } else {
            categoryRequestDto.setIsDefault(false);
        }

        log.info("Categoría agregada correctamente");
        return super.save(categoryRequestDto);
    }

    //agregar lógica usuario
    @Override
    public CategoryResponseDto update(CategoryUpdateDto categoryUpdateDto, Long id, String token) {

        Long userId = jwtUtil.extractUserId(token.substring(7));
        User user = userRepository.findById(id).get();

        Category category = categoryRepository.findById(id).get();

        if (user.getRole().getName().equals("ADMIN") || userId == category.getUserIdCreate()) {
            categoryUpdateDto.setId(id);
            categoryRepository.save(categoryMapper.updateToEntity(categoryUpdateDto));
            log.info("Categoría actualizada correctamente");
        } else {
            //exception
        }
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
