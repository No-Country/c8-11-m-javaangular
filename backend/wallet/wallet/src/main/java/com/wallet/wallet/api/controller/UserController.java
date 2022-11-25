package com.wallet.wallet.api.controller;

import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.domain.dto.request.AuthenticationRequest;
import com.wallet.wallet.domain.dto.request.UserRequestDto;
import com.wallet.wallet.domain.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "Guardar o actualizar un Usuario")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(userRequestDto.getEmail());
        authenticationRequest.setPassword(userRequestDto.getPassword());

        userService.save(userRequestDto);
        UserResponseDto userResponseDtoR = userService.authenticate(authenticationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDtoR);
    }

    @Operation(summary = "Log in de Usuario")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }

    @Operation(summary = "Pasar rol de Pending a USER")
    @PostMapping("/validate")
    public ResponseEntity<UserResponseDto> validate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(userService.validate( authenticationRequest));
    }

    /*
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un Usuario por Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResourceNotFoundException {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    */
}
