package com.spring.service;

import com.spring.entity.Section;

import java.util.List;

public interface SectionService {
    List<Section> getAllSections();

    Section getSectionByName(String name);

    int saveSection(String name);
}
