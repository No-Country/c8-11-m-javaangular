package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.domain.dto.request.AuthenticationRequest;
import com.wallet.wallet.domain.dto.request.UserRequestDto;
import com.wallet.wallet.domain.dto.response.UserResponseDto;
import com.wallet.wallet.domain.enums.ERole;
import com.wallet.wallet.domain.mapper.UserMapper;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.IUserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

   private final IUserRepository userRepository;
   private final UserMapper userMapper;
   private final PasswordEncoder encoder;


   @Override
   public UserResponseDto save(UserRequestDto userRequestDto) {
      var hashedPassword = encoder.encode(userRequestDto.getPassword());
      userRequestDto.setPassword(hashedPassword);

      var user = userMapper.requestDtoToEntity(userRequestDto);
      user.setRole(ERole.PENDING);
      userRepository.save(user);

      var response = userMapper.entityToResponseDto(user);
      response.setJwt(JwtUtil.generateToken(user));

      return response;
   }

   @Override
   public UserResponseDto validate(AuthenticationRequest authenticationRequest) {

      var user = getByEmail(authenticationRequest.getEmail());
      user.setRole(ERole.USER);
      userRepository.save(user);

      return authenticate(authenticationRequest);
   }

   @Override
   public UserResponseDto authenticate(AuthenticationRequest authenticationRequest) {
      var user = getByEmail(authenticationRequest.getEmail());

      if (encoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
         var userResponseDto = userMapper.entityToResponseDto(user);
         userResponseDto.setJwt(JwtUtil.generateToken(user));
         return userResponseDto;
      }

      return null;
   }

   public User getByEmail(String email) {
      return userRepository.findByEmail(email)
         .orElseThrow(() -> new RuntimeException("User not found"));
   }

}

