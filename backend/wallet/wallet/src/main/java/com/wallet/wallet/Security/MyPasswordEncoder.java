package com.wallet.wallet.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MyPasswordEncoder{
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public String encodePassword (String rawPassword) {
        return bCryptPasswordEncoder().encode(rawPassword);
    }
    public boolean matchesPassword(String rawPassword,String encodePassword){
        return bCryptPasswordEncoder().matches(rawPassword,encodePassword);
    }
}
