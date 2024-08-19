package com.spring.repository;

import com.spring.entity.Cheatsheet;

import java.util.List;

public interface CheatsheetInterface {
    List<Cheatsheet> getAllCheatsheets();

    int addCheatsheet(Cheatsheet cheatsheet);
}
