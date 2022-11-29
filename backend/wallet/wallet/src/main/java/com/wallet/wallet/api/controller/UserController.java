package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.domain.dto.request.AuthenticationRequest;
import com.wallet.wallet.domain.dto.request.UserRequestDto;
import com.wallet.wallet.domain.dto.response.UserResponseDto;
import com.wallet.wallet.handler.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.wallet.wallet.api.controller.ApiConstant.*;
import static com.wallet.wallet.handler.ResponseBuilder.*;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService service;

    @Operation(summary = "Guardar o actualizar un Usuario")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userRequestDto) {
        return responseBuilder(HttpStatus.CREATED, service.save(userRequestDto));
    }

    @Operation(summary = "Log in de Usuario")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return responseBuilder(HttpStatus.OK, service.authenticate(authenticationRequest));
    }

    @Operation(summary = "Pasar rol de Pending a USER")
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody AuthenticationRequest authenticationRequest) {
        return responseBuilder(HttpStatus.OK, service.validate(authenticationRequest));
    }

    @GetMapping
    //@PreAuthorize(USER_AUTHORITY)
    public ResponseEntity<?> test() {
        return responseBuilder(HttpStatus.OK, "hola");
    }
}
