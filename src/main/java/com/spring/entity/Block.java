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

    @ManyToOne(cascade = {
            CascadeType.DETACH,  CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH
    })
    @JoinColumn(name = "cheatsheet_id")
    private Cheatsheet cheatsheet;
}
