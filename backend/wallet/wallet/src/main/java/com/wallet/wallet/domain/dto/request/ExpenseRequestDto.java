package com.wallet.wallet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ExpenseRequestDto {

    @Schema(type = "double", example = "100.5")
    private Double amount;

    @Schema(type = "string", example = "")
    private String description;

    @Schema(type = "long", example = "1")
    private Long categoryId;

    @Schema(type = "long", example = "1")
    private Long currencyId;

    @Schema(type = "long", example = "1")
    private Long userId;

    @Schema(type = "localdate", example = "2022-11-30")
    private LocalDate date;

    @Schema(type = "boolean", example = "true")
    private Boolean isIncluded;

    @Schema(type = "boolean", example = "false")
    private Boolean deleted = Boolean.FALSE;
}
