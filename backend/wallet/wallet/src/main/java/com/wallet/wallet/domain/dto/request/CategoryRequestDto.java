package com.wallet.wallet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryRequestDto {

    @Schema(type = "string", example = "supermercado")
    private String name;

    @Schema(type = "string", example = "basket")
    private String icon;

    @Schema(type = "string", example = "#34EF23")
    private String colorCode;

    @Schema(type = "long", example = "1")
    private Long userIdCreate;

    @Schema(type = "boolean", example = "true")
    private Boolean isDefault;

    @Schema(type = "boolean", example = "false")
    private Boolean deleted = Boolean.FALSE;
}
