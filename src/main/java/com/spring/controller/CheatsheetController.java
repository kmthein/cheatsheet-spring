package com.spring.controller;

import com.spring.model.Cheatsheet;
import com.spring.model.Section;
import com.spring.repository.CheatsheetRepository;
import com.spring.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CheatsheetController {
    @Autowired
    private CheatsheetRepository cheatsheetRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping("/cheatsheets")
    public String getCheatsheets(Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetRepository.getAllCheatsheets();
        model.addAttribute("cheatsheets", cheatsheets) ;
        return "allCheatsheets";
    }

    @GetMapping("/cheatsheets/{id}")
    public String getCheatsheetById(@PathVariable int id, Model model) {
        Cheatsheet cheatsheet = cheatsheetRepository.getCheatsheetById(id);
        model.addAttribute("cs", cheatsheet);
        return "cheatsheetDetail";
    }

    @GetMapping("/add-cheatsheet")
    public ModelAndView getAddCheatsheetPage(Model model) {
        List<Section> sections = sectionRepository.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("createCheatsheet", "cheatsheet", new Cheatsheet());
    }
}
