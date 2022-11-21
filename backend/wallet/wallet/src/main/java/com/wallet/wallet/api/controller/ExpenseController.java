package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.response.CategoryGroupResponseDto;
import com.wallet.wallet.domain.dto.response.ExpenseResponseDto;
import com.wallet.wallet.domain.dto.response.HomeResponseDto;
import com.wallet.wallet.domain.model.Expense;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    @ApiOperation(value = "Find expenses by User id", hidden = true)
    @GetMapping("/user/{userId}")
    public  ResponseEntity<List<ExpenseResponseDto>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getAllByUserId(userId));
    }

    @ApiOperation(value = "Find information by User id for home", hidden = true)
    @GetMapping("/user/home/{userId}")
    public  ResponseEntity<HomeResponseDto> getForHome(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.getForHome(userId));
    }

    @ApiOperation(value = "Find balance by User id for Category name", hidden = true)
    @GetMapping("/categoryGroup/{userId}")
    public  ResponseEntity<Map<String, Double>> groupByCategoryByUserId(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(expenseService.groupByCategoryByUserId(userId));
    }

    @ApiOperation(value = "Delete a expense by id")
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
