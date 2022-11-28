package com.wallet.wallet.api.service.impl;

import com.wallet.wallet.Security.AuthenticationService;
import com.wallet.wallet.Security.MyPasswordEncoder;
import com.wallet.wallet.Security.jwt.JwtUtil;
import com.wallet.wallet.api.service.IUserService;
import com.wallet.wallet.domain.dto.request.AuthenticationRequest;
import com.wallet.wallet.domain.dto.request.UserRequestDto;
import com.wallet.wallet.domain.dto.response.UserResponseDto;
import com.wallet.wallet.domain.enums.ERole;
import com.wallet.wallet.domain.mapper.UserMapper;
import com.wallet.wallet.domain.model.Role;
import com.wallet.wallet.domain.model.User;
import com.wallet.wallet.domain.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;
    private UserMapper userMapper;

    private MyPasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private AuthenticationService authenticationService;

    @Override
    public com.wallet.wallet.domain.model.User findByEmail(String email) {
        com.wallet.wallet.domain.model.User user = userRepository.findByEmail(email).get();
        //UserDto userDto = userMapper.toUserDto(user);
        //logger.info("La busqueda fue exitosa: "+ userDto);
        return user;
    }

    @Override
    public com.wallet.wallet.domain.model.User findById(Long id) throws Exception {
        User user = checkId(id);
        //UserBookingDto userBookingDto = userMapper.toUserBookingDto(user);
//        userBookingDto.setName(user.getName());
//        userBookingDto.setSurname(user.getSurname());

        //userBookingDto.setEmail(user.getEmail());

        return user;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        String hashedPassword = passwordEncoder.encodePassword(userRequestDto.getPassword());
        userRequestDto.setPassword(hashedPassword);
        User user = userMapper.requestDtoToEntity(userRequestDto);

        Role role = new Role();
        role.setId(3L);
        role.setName(ERole.PENDING);

        user.setRole(role);

        userRepository.save(user);

        return userMapper.entityToResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto validate(AuthenticationRequest authenticationRequest) {

        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());

        Role role = new Role();
        role.setId(2L);
        role.setName(ERole.USER);

        user.get().setRole(role);

        userRepository.save(user.get());

        return authenticate(authenticationRequest);
    }

    @Override
    public UserResponseDto authenticate(AuthenticationRequest authenticationRequest) {
        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matchesPassword(authenticationRequest.getPassword(), user.get().getPassword())) {
            final UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            UserResponseDto userResponseDto = userMapper.entityToResponseDto(user.get());
            userResponseDto.setJwt(jwt);
            return userResponseDto;
        } else {
            System.out.println("arrojar excepción");
        }

        return new UserResponseDto();
    }

    //@Override
    //public void delete(Long id) throws ResourceNotFoundException {
    //    checkId(id);
    //    userRepository.deleteById(id);
    //    logger.info("Se elimino el User correctamente: id("+id+")");
    //}

    public User checkId(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception();
        }
        return user.get();
    }

}

