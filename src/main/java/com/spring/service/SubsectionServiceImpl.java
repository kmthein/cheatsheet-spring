package com.spring.service;

import com.spring.dto.ResponseDTO;
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
    public Subsection getSubsectionById(int id) {
        Subsection subsection = subsectionRepo.getSubsectionById(id);
        return subsection;
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

    @Override
    public ResponseDTO updateSubsection(Subsection subsection) {
        Subsection tempSubsection = subsectionRepo.getSubsectionById(subsection.getId());
        ResponseDTO responseDTO = new ResponseDTO();
        if(tempSubsection != null) {
            Section section = sectionRepo.getSectionById(subsection.getSection().getId());
            tempSubsection.setName(subsection.getName());
            tempSubsection.setSection(section);
            tempSubsection.setType(subsection.getType());
            int result = subsectionRepo.update(tempSubsection);
            if(result > 0) {
                responseDTO.setStatus("200");
                responseDTO.setMessage("Subsection update successful");
            } else {
                responseDTO.setStatus("404");
                responseDTO.setMessage("Update failed");
            }
        }
        return responseDTO;
    }
}
