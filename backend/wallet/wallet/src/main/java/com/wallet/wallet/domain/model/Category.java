package com.wallet.wallet.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
public class Category {

    //Damián

    @Id
    private Long id;
    private String name;
    private String icon;
    private Long userIdCreate;
    private Boolean isDefault;

    private Boolean softDelete;
}
