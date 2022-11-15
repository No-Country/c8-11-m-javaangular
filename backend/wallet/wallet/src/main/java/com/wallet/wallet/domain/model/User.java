package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    //Cristian

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //private Role role;

    //private List<Expense> expenses;
    //private List<Income> incomes;

    private Boolean softDelete;
}
