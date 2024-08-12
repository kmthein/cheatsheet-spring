package com.spring.controller;

import com.spring.dto.CheatsheetDTO;
import com.spring.model.Cheatsheet;
import com.spring.model.Section;
import com.spring.model.Subsection;
import com.spring.model.User;
import com.spring.repository.CheatsheetRepository;
import com.spring.repository.SectionRepository;
import com.spring.repository.SubsectionRepository;
import com.spring.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheatsheetController {
    @Autowired
    private CheatsheetRepository cheatsheetRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubsectionRepository subsectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @GetMapping("/home")
    public String getHomePage() {
        return "index";
    }
    @GetMapping("/cheatsheets")
    public String getCheatsheets(Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetRepository.getAllCheatsheets();
        List<Section> sections = sectionRepository.getAllSections();
        List<Subsection> subsections = subsectionRepository.getAllSubsections();
        model.addAttribute("cheatsheets", cheatsheets) ;
        model.addAttribute("sections", sections) ;
        model.addAttribute("subsections", subsections) ;
        return "allCheatsheets";
    }

    @GetMapping("/cheatsheets/{id}")
    public String getCheatsheetById(@PathVariable int id, Model model) {
        Cheatsheet cheatsheet = cheatsheetRepository.getCheatsheetById(id);
        model.addAttribute("cs", cheatsheet);
        return "cheatsheetDetail";
    }

    @GetMapping("/cheatsheets/section/{id}")
    public String cheatsheetFilterBySection(@PathVariable int id, Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetRepository.getCheatsheetsBySection(id);
        List<Section> sections = sectionRepository.getAllSections();
        List<Subsection> subsections = subsectionRepository.getAllSubsections();
        model.addAttribute("cheatsheets", cheatsheets) ;
        model.addAttribute("sections", sections) ;
        model.addAttribute("subsections", subsections) ;
        return "allCheatsheets";
    }

    @GetMapping("/add-cheatsheet")
    public ModelAndView getAddCheatsheetPage(Model model) {
        List<Section> sections = sectionRepository.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("createCheatsheet", "cheatsheetDTO", new CheatsheetDTO());
    }

    @PostMapping("/add-cheatsheet")
    public String addCheatsheet(CheatsheetDTO cheatsheetDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
//        User user = userRepository.getUserById(currentUser.getId());
        int sectionId = cheatsheetDTO.getSectionId();
//        Section section = sectionRepository.getSectionById(sectionId);
        int subsectionId = cheatsheetDTO.getSubsectionId();
//        Subsection subsection = subsectionRepository.getSubsectionById(subsectionId);
        String name = cheatsheetDTO.getName();
        String description = cheatsheetDTO.getDescription();
        String color = cheatsheetDTO.getColor();
        String style = cheatsheetDTO.getStyle();
        String type = cheatsheetDTO.getType();
        String language = cheatsheetDTO.getLanguage();
        String layout = cheatsheetDTO.getLayout();
        String title = request.getParameter("title");
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("<table>");
        contentBuilder.append("<tr><th>").append(title).append("</th><th></th></tr>");
        int columnNumber = 1;
        boolean isNewRow = true;
        while (request.getParameter("column" + columnNumber) != null) {
            if (isNewRow) {
                contentBuilder.append("<tr>");
                isNewRow = false;
            }
            String columnValue = request.getParameter("column" + columnNumber);
            contentBuilder.append("<td>").append(columnValue).append("</td>");

            if (columnNumber % 2 == 0) {
                contentBuilder.append("</tr>");
                isNewRow = true;
            }
            columnNumber++;
        }
        if (!isNewRow) {
            contentBuilder.append("</tr>");
        }
        contentBuilder.append("</table>");
        String content = contentBuilder.toString();
        int result = cheatsheetRepository.save(name, description, color, content, style, type, language, layout, currentUser.getId(), sectionId, subsectionId);
        if (result > 0) {
            return "redirect:cheatsheets";
        } else {
            return "createCheatsheet";
        }
    }
}
