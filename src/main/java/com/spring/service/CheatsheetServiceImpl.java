package com.spring.service;

import com.spring.dto.CheatsheetDTO;
import com.spring.entity.Cheatsheet;
import com.spring.entity.Section;
import com.spring.entity.Subsection;
import com.spring.entity.User;
import com.spring.repository.CheatsheetInterface;
import com.spring.repository.SectionInterface;
import com.spring.repository.SubsectionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CheatsheetServiceImpl implements CheatsheetService {
    @Autowired
    private CheatsheetInterface cheatsheetRepo;

    @Autowired
    private SectionInterface sectionRepo;

    @Autowired
    private SubsectionInterface subsectionRepo;
    @Override
    public List<Cheatsheet> getAllCheatsheets() {
        List<Cheatsheet> cheatsheets = cheatsheetRepo.getAllCheatsheets();
        return cheatsheets;
    }

    @Override
    public int addCheatsheet(CheatsheetDTO cheatsheetDTO, User user) {
        int sectionId = cheatsheetDTO.getSectionId();
        Cheatsheet cheatsheet = new Cheatsheet();
        Section section = sectionRepo.getSectionById(sectionId);
        int subsectionId = cheatsheetDTO.getSubsectionId();
        Subsection subsection = subsectionRepo.getSubsectionById(subsectionId);
        if(user == null) {
            return 0;
        } else {
            cheatsheet.setUser(user);
        }
        if(section != null && subsection != null) {
            cheatsheet.setSection(section);
            cheatsheet.setSubsection(subsection);
        }
        cheatsheet.setName(cheatsheetDTO.getName());
        cheatsheet.setDescription(cheatsheetDTO.getDescription());
        cheatsheet.setColor(cheatsheetDTO.getColor());
        cheatsheet.setStyle(cheatsheetDTO.getStyle());
        cheatsheet.setType(cheatsheetDTO.getType());
        cheatsheet.setLanguage(cheatsheetDTO.getLanguage());
        int result = cheatsheetRepo.addCheatsheet(cheatsheet);
        return result;
    }
}
