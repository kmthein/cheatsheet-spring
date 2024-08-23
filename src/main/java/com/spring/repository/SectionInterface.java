package com.spring.repository;

import com.spring.entity.Section;

import java.util.List;

public interface SectionInterface {
    List<Section> getAllSections();

    Section getSectionById(int id);

    Section getSectionByName(String name);

    int saveSection(Section section);
    int update(Section section);
}
