package com.spring.entity;

import com.spring.model.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private String website;
    private String description;
    @Enumerated(EnumType.STRING)
    private Role role;
}
