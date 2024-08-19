package com.spring.service;

import com.spring.dto.BlockDTO;
import com.spring.dto.CheatsheetDTO;
import com.spring.entity.*;
import com.spring.repository.CheatsheetInterface;
import com.spring.repository.SectionInterface;
import com.spring.repository.SubsectionInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class CheatsheetServiceImpl implements CheatsheetService {
    @Autowired
    private CheatsheetInterface cheatsheetRepo;

    @Autowired
    private SectionInterface sectionRepo;

    @Autowired
    private SubsectionInterface subsectionRepo;

    @Autowired
    private ModelMapper mapper;

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

    @Override
    public Cheatsheet getCheatsheetById(int id) {
        return cheatsheetRepo.getCheatsheetById(id);
    }

    @Override
    public List<Cheatsheet> getCheatsheetsByUser(int userId) {
        List<Cheatsheet> cheatsheetList = cheatsheetRepo.getCheatsheetsByUser(userId);
        return cheatsheetList;
    }

    @Override
    public List<Cheatsheet> getCheatsheetsBySection(int sectionId) {
        return cheatsheetRepo.getCheatsheetsBySection(sectionId);
    }

    @Override
    public int addBlock(String title, int cheatsheetId, String layout, Map<String, String> params) {
        int result = 0;
        Block tempBlock = new Block();
        Cheatsheet cheatsheet = cheatsheetRepo.getCheatsheetById(cheatsheetId);
        if (cheatsheet != null) {
            tempBlock.setCheatsheet(cheatsheet);
        }
        tempBlock.setBlockTitle(title);
        tempBlock.setLayout(String.valueOf(layout)); // Ensure layout is stored as a string
        Block block = cheatsheetRepo.saveBlock(tempBlock);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.matches("^cell\\[\\d+\\]\\[\\d+\\]$")) {
                try {
                    // Extract row and column from the key
                    String[] parts = key.substring(5, key.length() - 1).split("\\]\\[");
                    if (parts.length == 2) {
                        int row = Integer.parseInt(parts[0]);
                        int col = Integer.parseInt(parts[1]);

                        Cell cell = new Cell();
                        cell.setBlock(block);
                        cell.setRowNum(row);
                        cell.setColNum(col);
                        cell.setContent(value);

                        // Persist the cell
                        result = cheatsheetRepo.saveCells(cell);
                    } else {
                        System.err.println("Unexpected cell format: " + key);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("NumberFormatException for key: " + key + ", value: " + value);
                    e.printStackTrace();
                }
            } else {
                System.out.println("Skipping non-cell parameter: " + key);
            }
        }
        return result;
    }

    @Override
    public List<BlockDTO> getBlocksForCheatsheet(int cheatsheetId) {
        return cheatsheetRepo.getBlocksByCheatsheet(cheatsheetId);
    }

    @Override
    public CheatsheetDTO convertToDTO(Cheatsheet cheatsheet) {
        CheatsheetDTO cheatsheetDTO = mapper.map(cheatsheet, CheatsheetDTO.class);

        // Custom field mapping (if necessary), for example, formatting createdAt
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        cheatsheetDTO.setFormattedCreatedAt(cheatsheet.getCreatedAt().format(formatter));
        cheatsheetDTO.setUserName(cheatsheet.getUser().getName());
        return cheatsheetDTO;
    }
}
