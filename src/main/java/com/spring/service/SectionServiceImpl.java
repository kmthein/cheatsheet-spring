package com.spring.service;

import com.spring.entity.Section;
import com.spring.repository.SectionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionInterface sectionRepo;
    @Override
    public List<Section> getAllSections() {
        return sectionRepo.getAllSections();
    }

    @Override
    public Section getSectionByName(String name) {
        return sectionRepo.getSectionByName(name);
    }

    @Override
    public int saveSection(String name) {
        Section section = new Section();
        section.setName(name);
        int result = sectionRepo.saveSection(section);
        return result;
    }
}
