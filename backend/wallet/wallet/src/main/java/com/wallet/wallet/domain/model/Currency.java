package com.wallet.wallet.domain.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
@Data
@Builder
public class Currency {

    @Id
    private Long id;

    //@Schema (example = "ARS")
    private String codeCurrency;
    private Double value;

}
