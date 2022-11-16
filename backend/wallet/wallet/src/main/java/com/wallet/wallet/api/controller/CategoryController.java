package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryResponseDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.model.Category;
import com.wallet.wallet.domain.model.Expense;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Api(tags = "Category", description = " " )
public class CategoryController {

    private final ICategoryService categoryService;

    @ApiOperation(value = "Register a new category")
    @PostMapping("/save")
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryRequestDto));
    }

    @ApiOperation(value = "Delete a category by id")
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

