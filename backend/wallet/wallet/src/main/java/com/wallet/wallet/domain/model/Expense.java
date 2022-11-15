package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
public class Expense {

    //Damián

    @Id
    @SequenceGenerator(name = "expenseSequence",sequenceName = "expenseSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenseSequence")
    private Long id;

    private Double amount;
    private String description;
    //private Category category;
    //private Currency currency;
    private LocalDate date;
    private Boolean isIncluded;

    private Boolean softDelete;
}
