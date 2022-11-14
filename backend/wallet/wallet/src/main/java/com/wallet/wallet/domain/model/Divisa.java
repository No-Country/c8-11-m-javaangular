package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "divisas")
@Data
public class Divisa {

    @Id
    private Long id;

    //@Schema (example = "ARS")
    private String name;
    private Double valueDollar;

}
