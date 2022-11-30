package com.wallet.wallet.api.controller;


import com.wallet.wallet.api.service.IAboutUsService;
import com.wallet.wallet.domain.dto.request.AboutUsRequestDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/aboutUs")
@AllArgsConstructor
@Api(tags = "AboutUs", description = " " )
public class AboutUsController {

    private final IAboutUsService aboutUsService;

    @ApiOperation(value = "Add a new card")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AboutUsRequestDto aboutUsRequestDto){
        return responseBuilder(HttpStatus.CREATED, aboutUsService.save(aboutUsRequestDto));
    }

    @ApiOperation(value = "Find all cards")
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return responseBuilder(HttpStatus.OK, aboutUsService.findAll());
    }

    @ApiOperation(value = "Delete a card by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        aboutUsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
