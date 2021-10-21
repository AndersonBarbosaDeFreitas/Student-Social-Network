package com.dsc.student_social_network.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    public User() {
        super();
    }
}
