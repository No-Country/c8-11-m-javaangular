package com.wallet.wallet.domain.dto.request;


import com.wallet.wallet.domain.enums.ERoleAboutUs;
import lombok.Data;

@Data
public class AboutUsRequestDto {

    private Long id;

    private String nameComplete;

    private ERoleAboutUs role;

    private String description;

    private String image;

    private String linkedIn;

    private String github;

    private String email;

}
