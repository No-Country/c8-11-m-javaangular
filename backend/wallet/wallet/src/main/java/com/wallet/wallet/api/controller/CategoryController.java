package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.ICategoryService;
import com.wallet.wallet.domain.dto.request.CategoryRequestDto;
import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Api(tags = "Category", description = " " )
public class CategoryController {

    private final ICategoryService categoryService;

    @ApiOperation(value = "Register a new category")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CategoryRequestDto categoryRequestDto, @RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.CREATED, categoryService.save(categoryRequestDto, token));
    }

    @ApiOperation(value = "Update a category by Id")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> save(@RequestBody CategoryUpdateDto categoryUpdateDto, @PathVariable Long id, @RequestHeader("Authorization") String token){
    //public ResponseEntity<?> update(@RequestBody CategoryUpdateDto categoryUpdateDto, @PathVariable Long id){
        return responseBuilder(HttpStatus.OK, categoryService.update(categoryUpdateDto, id, token));
    }

    @ApiOperation(value = "Find a category by Id")
    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getById/{id}")
    public  ResponseEntity<?> getOne(@PathVariable Long id){
        return responseBuilder(HttpStatus.OK, categoryService.getById(id));
    }

    @ApiOperation(value = "Find all categories")
    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAll")
    public  ResponseEntity<?> getAll(){
        return responseBuilder(HttpStatus.OK, categoryService.getAll());
    }

    @ApiOperation(value = "Find all categories by User id")
    //@PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllByUserId")
    public  ResponseEntity<?> getAllByUserId(Long userId){
        return responseBuilder(HttpStatus.OK, categoryService.getAllByUserId(userId));
    }

    @ApiOperation(value = "Delete a category by Id")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

