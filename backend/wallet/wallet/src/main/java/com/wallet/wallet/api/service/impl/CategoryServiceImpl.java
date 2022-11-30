package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.api.service.generic.GenericServiceImpl;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import static com.wallet.wallet.domain.enums.EMessageCode.*;
import com.wallet.wallet.domain.enums.ERole;
import com.wallet.wallet.domain.mapper.CategoryMapper;
import com.wallet.wallet.domain.mapper.IMapper;
import com.wallet.wallet.domain.model.Category;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.ICategoryRepository;
import com.wallet.wallet.handler.exeption.UserUnauthorizedException;

import lombok.AllArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryResponseDto, CategoryRequestDto, Long>
        implements ICategoryService {

    private final CategoryMapper categoryMapper;
    private final ICategoryRepository repository;
    private final IUserService userService;;
    private final JwtUtil jwtUtil;
    private final MessageSource messenger;

    @Override
    public CategoryResponseDto save(CategoryRequestDto dto, String token) {
        if(tokenNotValid(token)){
            throw new BadCredentialsException(messenger.getMessage(INVALID_TOKEN.name(), null, Locale.getDefault()));
        }

        Long id = jwtUtil.extractUserId(token);
        User user = userService.getById(id);

        dto.setUserIdCreate(id);
        dto.setIsDefault(user.getRole().equals(ERole.ADMIN) ? true : false);

        return super.save(dto);
    }

    // agregar lógica usuario
    @Override
    public CategoryResponseDto update(CategoryUpdateDto dto, Long id, String token) {
        if(tokenNotValid(token)){
            throw new BadCredentialsException(messenger.getMessage(INVALID_TOKEN.name(), null, Locale.getDefault()));
        }

        Long userId = jwtUtil.extractUserId(token);
        User user = userService.getById(id);

        Category category = repository.findById(id).get();

        if (user.getRole().equals(ERole.ADMIN) || userId.equals(category.getUserIdCreate())) {
            dto.setId(id);
            repository.save(categoryMapper.updateToEntity(dto));
        } else {
            throw new UserUnauthorizedException(messenger.getMessage(USER_UNAUTHORIZED.name(),
                    new Object[] {userId, category.getUserIdCreate()}, Locale.getDefault()));
        }
        return getById(id);
    }

    // agregar excepción en el generic
    @Override
    public CategoryResponseDto getById(Long Id) {
        return categoryMapper.entityToResponseDto(repository.findById(Id).get());
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryMapper.listEntityToListResponseDto(repository.findAll());
    }

    // agregar lógica usuario en el controller
    @Override
    public List<CategoryResponseDto> getAllByUserId(Long userId) {
        return categoryMapper.listEntityToListResponseDto(repository.getAllByUserId(userId));
    }

    // agregar excepción en el generic
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public JpaRepository<Category, Long> getRepository() {
        return repository;
    }

    @Override
    public IMapper<Category, CategoryResponseDto, CategoryRequestDto> getMapper() {
        return categoryMapper;
    }
}
