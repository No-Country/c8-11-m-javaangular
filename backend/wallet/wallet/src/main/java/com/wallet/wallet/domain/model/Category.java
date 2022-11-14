package com.wallet.wallet.domain.model;

import javax.persistence.Id;

public class Category {

    @Id
    private Long id;
    private Long userIdCreate;
    private Boolean isDefault;
}
