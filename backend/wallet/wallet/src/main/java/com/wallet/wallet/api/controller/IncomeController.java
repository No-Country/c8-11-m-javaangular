package com.wallet.wallet.api.controller;

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;
import com.wallet.wallet.api.service.IIncomeService;
import com.wallet.wallet.domain.dto.request.IncomeRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incomes")
@Api(tags = "Income", description = " " )
public record IncomeController(IIncomeService service) {

    @ApiOperation(value = "Save a new income")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody IncomeRequestDto dto){
        return responseBuilder(CREATED, service.save(dto));
    }

    @ApiOperation(value = "Find an income by id")
    @GetMapping("/findOne/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) {
        return responseBuilder(OK, service.findById(id));
    }

    @ApiOperation(value = "Find all incomes")
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return responseBuilder(OK, service.findAll());
    }

    @ApiOperation(value = "Delete an income by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }


}
