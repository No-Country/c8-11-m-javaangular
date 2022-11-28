package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.ILanguageService;
import com.wallet.wallet.domain.dto.request.LanguageRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/languages")
@AllArgsConstructor
@Api(tags = "Languages", description = " " )
public class LanguageController {

    private final ILanguageService languageService;

    @ApiOperation(value = "Add a new language")
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody LanguageRequestDto languageRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(languageService.save(languageRequestDto));
    }

    @ApiOperation(value = "Delete a language by id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        languageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
