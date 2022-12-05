package com.wallet.wallet.domain.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;


}