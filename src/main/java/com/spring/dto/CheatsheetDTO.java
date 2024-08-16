package com.spring.dto;

import com.spring.model.Base;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheatsheetDTO extends Base  {
    private String name, description, color, content, style, type, language, layout;
    private int userId, sectionId, subsectionId;
}
