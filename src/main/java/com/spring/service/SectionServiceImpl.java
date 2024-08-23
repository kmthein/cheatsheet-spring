package com.spring.service;

import com.spring.dto.ResponseDTO;
import com.spring.entity.Section;
import com.spring.entity.Subsection;
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

    @Override
    public Section getSectionById(int id) {
        return sectionRepo.getSectionById(id);
    }

    @Override
    public ResponseDTO updateSection(Section section) {
        ResponseDTO responseDTO = new ResponseDTO();
        Section sectionNameExist = sectionRepo.getSectionByName(section.getName());
        if(sectionNameExist != null) {
            responseDTO.setStatus("500");
            responseDTO.setMessage("Section name already existed");
            return responseDTO;
        }
        Section tempSection = sectionRepo.getSectionById(section.getId());
        if(tempSection != null) {
            tempSection.setName(section.getName());
            int result = sectionRepo.update(tempSection);
            if(result > 0) {
                responseDTO.setStatus("200");
                responseDTO.setMessage("Update success");
            } else {
                responseDTO.setStatus("403");
                responseDTO.setMessage("Update failed");
            }
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteById(int id) {
        Section section = sectionRepo.getSectionById(id);
        if(section != null) {
            section.setIsDeleted(true);
        }
        int result = sectionRepo.update(section);
        ResponseDTO responseDTO = new ResponseDTO();
        if(result >  0) {
            responseDTO.setStatus("200");
            responseDTO.setMessage("Delete success");
        } else {
            responseDTO.setStatus("403");
            responseDTO.setMessage("Delete failed");
        }
        return responseDTO;
    }
}
