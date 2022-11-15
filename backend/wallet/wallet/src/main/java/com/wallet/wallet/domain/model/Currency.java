package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
@Data
public class Currency {

    //Lucas l.

    @Id
    private Long id;

    //@Schema (example = "ARS")
    private String codeCurrency;
    //@Schema (example = 0.0016)
    private Double valueDollar;

}
