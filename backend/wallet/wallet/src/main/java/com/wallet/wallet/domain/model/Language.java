package com.wallet.wallet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "lenguages")
@Data
@SQLDelete(sql = "UPDATE lenguages SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Language {

    @Id
    @SequenceGenerator(name = "lenguageSequence",sequenceName = "lenguageSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lenguageSequence")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "")
    private String name;

    //@Column(nullable = false)
    //@NotNull(message = "")
    private String flag;

}
