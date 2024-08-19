package com.spring.dto;

import com.spring.entity.Base;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheatsheetDTO  {
    private String name, description, color, style, type, language;
    private int userId, sectionId, subsectionId;
}
