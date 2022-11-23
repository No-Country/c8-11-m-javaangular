package com.wallet.wallet.domain.dto.request;

import lombok.Data;

@Data
public class CategoryUpdateDto {

    private Long id;

    private String name;

    private String icon;

    private String colorCode;

}
