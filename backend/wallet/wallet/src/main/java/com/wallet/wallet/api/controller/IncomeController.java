package com.wallet.wallet.api.controller;


import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/incomes")
@AllArgsConstructor
@Api(tags = "Income", description = " " )
public class IncomeController {

    private final IIncomeService incomeService;

    @ApiOperation(value = "Save a new income")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody IncomeRequestDto incomeRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(incomeService.save(incomeRequestDto));
    }

    @ApiOperation(value = "Find an income by id")
    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(incomeService.findById(id));
    }

    @ApiOperation(value = "Find all incomes")
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(incomeService.findAll());
    }

    @ApiOperation(value = "Delete an income by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        incomeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
