package com.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "block")
public class Block extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "block_title")
    private String blockTitle;

    @Column(name = "layout")
    private String layout;

    @ManyToOne
    @JoinColumn(name = "cheatsheet_id")
    private Cheatsheet cheatsheet;
}
