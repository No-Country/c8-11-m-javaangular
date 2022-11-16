package com.wallet.wallet.domain.model;

import com.wallet.wallet.domain.enums.ERoleAboutUs;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "aboutUs")
@Data
public class AboutUs {

    //Lucas g.

    @Id
    @SequenceGenerator(name = "aboutUsSequence",sequenceName = "aboutUsSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aboutUsSequence")
    private Long id;

    private String nameComplete;
    private ERoleAboutUs role;
    private String description;
    private String image;
    private String linkedIn;
    private String github;
    private String email;

}
