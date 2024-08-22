package com.spring.service;

import com.spring.dto.ResponseDTO;
import com.spring.entity.Subsection;

import java.util.List;

public interface SubsectionService {
    List<Subsection> getAllSubsections();

    List<Subsection> getSubsectionBySectionId(int sectionId);

    Subsection getSubsectionByName(String name);

    Subsection getSubsectionById(int id);

    int saveSubsection(String name, String type, int sectionId);

    ResponseDTO updateSubsection(Subsection subsection);
}
