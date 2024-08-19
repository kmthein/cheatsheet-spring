package com.spring.service;

import com.spring.dto.BlockDTO;
import com.spring.dto.CheatsheetDTO;
import com.spring.entity.Block;
import com.spring.entity.Cheatsheet;
import com.spring.entity.User;

import java.util.List;
import java.util.Map;

public interface CheatsheetService {
    List<Cheatsheet> getAllCheatsheets();

    int addCheatsheet(CheatsheetDTO cheatsheetDTO, User user);

    List<Cheatsheet> getCheatsheetsByUser(int userId);

    Cheatsheet getCheatsheetById(int id);

    List<Cheatsheet> getCheatsheetsBySection(int sectionId);

    int addBlock(String title, int cheatsheetId, String layout, Map<String, String> params);

    List<BlockDTO> getBlocksForCheatsheet(int cheatsheetId);

    CheatsheetDTO convertToDTO(Cheatsheet cheatsheet);
}
