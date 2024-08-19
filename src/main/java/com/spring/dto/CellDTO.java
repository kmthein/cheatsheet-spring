package com.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellDTO {
    private int colNum;
    private String content;

    public CellDTO(int colNum, String content) {
        this.colNum = colNum;
        this.content = content;
    }
}
