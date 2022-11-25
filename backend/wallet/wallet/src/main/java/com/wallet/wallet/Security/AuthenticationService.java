package com.wallet.wallet.Security;

import com.wallet.wallet.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.wallet.wallet.domain.model.User user = userRepository.findByEmail(email).get();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getName().name());
        return new User(user.getEmail(), user.getPassword(), Collections.singletonList(grantedAuthority));
    }
}
