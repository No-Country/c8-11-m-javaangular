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

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
@Api(tags = "Expense", description = " " )
public class ExpenseController {

    private final IExpenseService expenseService;

    @ApiOperation(value = "Register a new expense")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> save(@RequestBody ExpenseRequestDto expenseRequestDto, @RequestHeader("Authorization") String token) {
        return responseBuilder(HttpStatus.CREATED, expenseService.save(expenseRequestDto, token));
    }

    @ApiOperation(value = "Find expenses by User", hidden = true)
    @GetMapping("/user")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> getByUserId(@RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.OK, expenseService.getAllByUserId(token));
    }

    @ApiOperation(value = "Find information by User for home", hidden = true)
    @GetMapping("/user/home")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> getForHome(@RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.OK, expenseService.getForHome(token));
    }

    @ApiOperation(value = "Find balance by User id for Category name", hidden = true)
    @GetMapping("/categoryGroup")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> groupByCategoryByUserId(@RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.OK, expenseService.groupByCategoryByUserId(token));
    }

    @ApiOperation(value = "Find by Filter and Order by User", hidden = true)
    @GetMapping("/filter")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> filter(@RequestParam(required = false) List<Long> categoriesId,
                                     @RequestParam(required = false) Double amountMin,
                                     @RequestParam(required = false) Double amountMax,
                                     @RequestParam( defaultValue = "date") String orderBy,
                                     @RequestParam( defaultValue = "DESC") String order,
                                     @RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.OK, expenseService.filter(token, categoriesId, amountMin, amountMax, orderBy, order));
    }

    @ApiOperation(value = "Delete a expense by id")
    @GetMapping("/statistics")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getStatistics(@RequestHeader("Authorization") String token){
        return responseBuilder(HttpStatus.OK, expenseService.getStatistics(token));
    }

    @ApiOperation(value = "Delete a expense by id")
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> delete(@PathVariable Long id, @RequestHeader("Authorization") String token){
        expenseService.delete(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
