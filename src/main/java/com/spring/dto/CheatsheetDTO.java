package com.spring.dto;

import com.spring.entity.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheatsheetDTO  {
    private int id;
    private String name, description, color, style, type, language;
    private LocalDateTime createdAt;
    private String formattedCreatedAt;
    private String userName;
    private int userId, sectionId, subsectionId;
}
