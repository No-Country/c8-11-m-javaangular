package com.wallet.wallet.api.service;

import com.wallet.wallet.domain.dto.request.AuthenticationRequest;
import com.wallet.wallet.domain.dto.request.UserRequestDto;
import com.wallet.wallet.domain.dto.response.UserResponseDto;
import com.wallet.wallet.domain.model.User;

public interface IUserService {

    User findByEmail(String email);

    User findById(Long id) throws Exception;

    UserResponseDto validate(AuthenticationRequest authenticationRequest);

    UserResponseDto authenticate(AuthenticationRequest authenticationRequest);

    UserResponseDto save(UserRequestDto userRequestDto);

}
