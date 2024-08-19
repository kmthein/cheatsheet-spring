package com.spring.service;

import com.spring.entity.Section;
import com.spring.entity.Subsection;
import com.spring.repository.SectionInterface;
import com.spring.repository.SubsectionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubsectionServiceImpl implements SubsectionService {
    @Autowired
    private SubsectionInterface subsectionRepo;

    @Autowired
    private SectionInterface sectionRepo;

    @Override
    public List<Subsection> getAllSubsections() {
        return subsectionRepo.getAllSubsections();
    }

    @Override
    public List<Subsection> getSubsectionBySectionId(int sectionId) {
        List<Subsection> subsectionList = subsectionRepo.getSubsectionBySectionId(sectionId);
        return subsectionList;
    }

    @Override
    public Subsection getSubsectionByName(String name) {
        return null;
    }

    @Override
    public int saveSubsection(String name, String type, int sectionId) {
        Section section = sectionRepo.getSectionById(sectionId);
        Subsection subsection = new Subsection();
        if(section != null) {
            subsection.setSection(section);
        }
        subsection.setName(name);
        subsection.setType(type);
        return subsectionRepo.save(subsection);
    }
}
