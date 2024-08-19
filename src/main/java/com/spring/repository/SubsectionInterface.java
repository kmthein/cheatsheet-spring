package com.spring.repository;

import com.spring.entity.Subsection;

import java.util.List;

public interface SubsectionInterface {
    List<Subsection> getAllSubsections();
    List<Subsection> getSubsectionBySectionId(int sectionId);
    Subsection getSubsectionById(int id);
    Subsection getSubsectionByName(String name);

    int save(Subsection subsection);
}
