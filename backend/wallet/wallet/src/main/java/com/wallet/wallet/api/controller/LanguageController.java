package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.ILanguageService;
import com.wallet.wallet.domain.dto.request.LanguageRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wallet.wallet.handler.ResponseBuilder.responseBuilder;

@RestController
@RequestMapping("/languages")
@AllArgsConstructor
@Api(tags = "Languages", description = " " )
public class LanguageController {

    private final ILanguageService languageService;

    @ApiOperation(value = "Add a new language")
    @PostMapping("/save")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> save(@RequestBody LanguageRequestDto languageRequestDto){
        return responseBuilder(HttpStatus.CREATED, languageService.save(languageRequestDto));
    }

    @ApiOperation(value = "Delete a language by id")
    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        languageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
