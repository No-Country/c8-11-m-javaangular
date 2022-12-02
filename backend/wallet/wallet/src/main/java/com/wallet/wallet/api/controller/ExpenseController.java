package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.IExpenseService;
import com.wallet.wallet.domain.dto.request.CategoryUpdateDto;
import com.wallet.wallet.domain.dto.request.ExpenseRequestDto;
import com.wallet.wallet.domain.dto.request.ExpenseUpdateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.HttpStatus.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/expenses")
@Api(tags = "Expense", description = " " )
public record ExpenseController(IExpenseService service) {

    @ApiOperation(value = "Register a new expense")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> save(@RequestBody ExpenseRequestDto dto, @RequestHeader("Authorization") String token) {
        return responseBuilder(CREATED, service.save(dto, token));
    }

    @ApiOperation(value = "Update a expense by Id")
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ExpenseUpdateDto expenseUpdateDto,
                                    @PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        return responseBuilder(OK, service.update(expenseUpdateDto, id, token));
    }

    @ApiOperation(value = "Find expenses by User", hidden = true)
    @GetMapping("/user")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> getByUserId(@RequestHeader("Authorization") String token){
        return responseBuilder(OK, service.getAllByUserId(token));
    }

    @ApiOperation(value = "Find information by User for home", hidden = true)
    @GetMapping("/user/home")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> getForHome(@RequestHeader("Authorization") String token){
        return responseBuilder(OK, service.getForHome(token));
    }

    @ApiOperation(value = "Find balance by User id for Category name", hidden = true)
    @GetMapping("/categoryGroup")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> groupByCategoryByUserId(@RequestHeader("Authorization") String token){
        return responseBuilder(OK, service.groupByCategoryByUserId(token));
    }

    @ApiOperation(value = "Find by Filter and Order by User", hidden = true)
    @GetMapping("/filter")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<?> filter(@RequestParam(required = false) List<Long> categoriesId,
                                     @RequestParam(required = false) Double amountMin,
                                     @RequestParam(required = false) Double amountMax,
                                     @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate start,
                                     @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end,
                                     @RequestParam( defaultValue = "date") String orderBy,
                                     @RequestParam( defaultValue = "DESC") String order,
                                     @RequestHeader("Authorization") String token){

        return responseBuilder(OK, service.filter(token, categoriesId, amountMin, amountMax, start, end, orderBy, order));
    }

    @ApiOperation(value = "Delete a expense by id")
    @GetMapping("/statistics")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> getStatistics(@RequestHeader("Authorization") String token){
        return responseBuilder(OK, service.getStatistics(token));
    }

    @ApiOperation(value = "Delete a expense by id")
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<Void> delete(@PathVariable Long id, @RequestHeader("Authorization") String token){
        service.delete(id, token);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
