package com.spring.service;

import com.spring.entity.Subsection;

import java.util.List;

public interface SubsectionService {
    List<Subsection> getAllSubsections();

    List<Subsection> getSubsectionBySectionId(int sectionId);

    Subsection getSubsectionByName(String name);

    int saveSubsection(String name, String type, int sectionId);
}
