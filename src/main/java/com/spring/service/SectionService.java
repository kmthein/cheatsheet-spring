package com.spring.service;

import com.spring.dto.ResponseDTO;
import com.spring.entity.Section;

import java.util.List;

public interface SectionService {
    List<Section> getAllSections();
    Section getSectionByName(String name);
    int saveSection(String name);
    Section getSectionById(int id);
    ResponseDTO updateSection(Section section);
    ResponseDTO deleteById(int id);
}
