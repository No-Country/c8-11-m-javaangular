package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.model.Expense;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
@Api(tags = "Expense", description = " " )
public class ExpenseController {

    private final IExpenseService expenseService;

    @ApiOperation(value = "Register a new expense")
    @PostMapping("/save")
    public ResponseEntity<ExpenseResponseDto> save(@RequestBody ExpenseRequestDto expenseRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.save(expenseRequestDto));
    }

    @ApiOperation(value = "Find a expense by id", hidden = true)
    @GetMapping("/findOne/{id}")
    public  ResponseEntity<ExpenseResponseDto> findOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.findOne(id));
    }

    @ApiOperation(value = "Delete a expense by id")
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
