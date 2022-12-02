package com.wallet.wallet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@SQLDelete(sql = "UPDATE expenses SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Expense {

    @Id
    @SequenceGenerator(name = "expenseSequence",sequenceName = "expenseSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenseSequence")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "")
    @Min(value = 0, message = "")
    private Double amount;

    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "")
    private Currency currency;

    @Column(nullable = false)
    @NotNull(message = "")
    private LocalDate date;

    private Boolean isIncluded;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "")
    private User user;

    private Boolean deleted = Boolean.FALSE;
}
