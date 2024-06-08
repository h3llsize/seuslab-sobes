package com.seuslab.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_f_name")
    private String firstName;

    @Column(name = "user_l_name")
    private String lastName;

    @Column(name = "user_b_date")
    private String birthDate;

    @Column(name = "user_city")
    private String city;

    @Column(name = "user_contacts")
    private String contacts;
}
