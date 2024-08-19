package com.spring.repository;

import com.spring.dto.BlockDTO;
import com.spring.entity.Block;
import com.spring.entity.Cell;
import com.spring.entity.Cheatsheet;

import java.util.List;

public interface CheatsheetInterface {
    List<Cheatsheet> getAllCheatsheets();

    int addCheatsheet(Cheatsheet cheatsheet);

    List<Cheatsheet> getCheatsheetsByUser(int userId);

    Cheatsheet getCheatsheetById(int id);

    List<Cheatsheet> getCheatsheetsBySection(int sectionId);

    Block saveBlock(Block block);

    int saveCells(Cell cell);

    Block findBlockById(int id);

    List<BlockDTO> getBlocksByCheatsheet(int cheatsheetId);
}
