package com.spring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cell extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "row_num")
    private int rowNum;

    @Column(name = "col_num")
    private int colNum;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;
}
