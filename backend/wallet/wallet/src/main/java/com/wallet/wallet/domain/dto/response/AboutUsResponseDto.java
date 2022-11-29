package com.wallet.wallet.domain.dto.response;

import com.wallet.wallet.domain.enums.ERoleAboutUs;
import lombok.Data;

@Data
public class AboutUsResponseDto {

    private Long id;

    private String nameComplete;

    private String role;

    private String description;

    private String image;

    private String linkedIn;

    private String github;

    private String email;
}
