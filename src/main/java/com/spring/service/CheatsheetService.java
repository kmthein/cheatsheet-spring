package com.spring.service;

import com.spring.dto.CheatsheetDTO;
import com.spring.entity.Cheatsheet;
import com.spring.entity.User;

import java.util.List;

public interface CheatsheetService {
    List<Cheatsheet> getAllCheatsheets();

    int addCheatsheet(CheatsheetDTO cheatsheetDTO, User user);
}
