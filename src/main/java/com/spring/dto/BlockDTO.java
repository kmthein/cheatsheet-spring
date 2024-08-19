package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BlockDTO {
    private int blockId;
    private String title;
    private String description;
    private List<List<CellDTO>> rows;

    public BlockDTO(int blockId, String title, String description, List<List<CellDTO>> rows) {
        this.blockId = blockId;
        this.title = title;
        this.description = description;
        this.rows = rows;
    }
}
