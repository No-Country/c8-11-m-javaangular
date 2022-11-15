package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@Data
public class Income {

    //Lucas g.

    @Id
    @SequenceGenerator(name = "incomeSequence",sequenceName = "incomeSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incomeSequence")
    private Long id;

    @NotNull()
    private Double amount;
    private String description;
    //private Currency currency;
    private LocalDate date;
    private Boolean isIncluded;
    private EIncome type;

    private Boolean softDelete;
}
