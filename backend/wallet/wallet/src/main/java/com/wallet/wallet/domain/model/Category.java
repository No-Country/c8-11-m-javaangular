package com.wallet.wallet.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Data
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Category {

    @Id
    @SequenceGenerator(name = "categorySequence",sequenceName = "categorySequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String icon;

    @Column(nullable = false)
    private String colorCode;

    private Long userIdCreate;

    private Boolean isDefault;

    private Boolean deleted = Boolean.FALSE;
}
