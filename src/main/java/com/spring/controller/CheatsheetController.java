package com.spring.controller;

import com.spring.dto.BlockDTO;
import com.spring.dto.CheatsheetDTO;
import com.spring.entity.*;
import com.spring.service.CheatsheetService;
import com.spring.service.SectionService;
import com.spring.service.SubsectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CheatsheetController {
    @Autowired
    private CheatsheetService cheatsheetService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SubsectionService subsectionService;

    @Autowired
    ModelMapper mapper;

    @GetMapping("/home")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/cheatsheets")
    public String getCheatsheets(Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetService.getAllCheatsheets();
        List<CheatsheetDTO> cheatsheetDTOs = cheatsheets.stream()
                .map(cheatsheetService::convertToDTO)
                .collect(Collectors.toList());
        List<Section> sections = sectionService.getAllSections();
        List<Subsection> subsections = subsectionService.getAllSubsections();
        model.addAttribute("cheatsheets", cheatsheetDTOs);
        model.addAttribute("sections", sections);
        model.addAttribute("subsections", subsections);
        return "allCheatsheets";
    }

    @GetMapping("/cheatsheets/user/{id}")
    public String getCheatsheetsByUser(@PathVariable int id, Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetService.getCheatsheetsByUser(id);
        List<CheatsheetDTO> cheatsheetDTOs = cheatsheets.stream()
                .map(cheatsheetService::convertToDTO)
                .collect(Collectors.toList());
        List<Section> sections = sectionService.getAllSections();
        List<Subsection> subsections = subsectionService.getAllSubsections();
        model.addAttribute("cheatsheets", cheatsheetDTOs);
        model.addAttribute("sections", sections);
        model.addAttribute("subsections", subsections);
        return "userCheatsheets";
    }

    @GetMapping("/add-cheatsheet")
    public Object getAddCheatsheetPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/home";
        }
        List<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("createCheatsheet", "cheatsheetDTO", new CheatsheetDTO());
    }

    @PostMapping("/add-cheatsheet")
    public String addCheatsheet(CheatsheetDTO cheatsheetDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        int result = cheatsheetService.addCheatsheet(cheatsheetDTO, currentUser);
        if (result > 0) {
            return "redirect:/cheatsheets";
        } else {
            return "createCheatsheet";
        }
    }

    @GetMapping("/cheatsheets/{id}")
    public String getCheatsheetById(@PathVariable int id, Model model) {
        Cheatsheet cheatsheet = cheatsheetService.getCheatsheetById(id);
        List<BlockDTO> blockDTOList = cheatsheetService.getBlocksForCheatsheet(id);
        model.addAttribute("cs", cheatsheet);
        model.addAttribute("blocks", blockDTOList);
        return "cheatsheetDetail";
    }

    @GetMapping("/cheatsheets/section/{id}")
    public String cheatsheetFilterBySection(@PathVariable int id, Model model) {
        List<Cheatsheet> cheatsheets = cheatsheetService.getCheatsheetsBySection(id);
        List<CheatsheetDTO> cheatsheetDTOs = cheatsheets.stream()
                .map(cheatsheetService::convertToDTO)
                .collect(Collectors.toList());
        List<Section> sections = sectionService.getAllSections();
        List<Subsection> subsections = subsectionService.getAllSubsections();
        model.addAttribute("cheatsheets", cheatsheetDTOs);
        model.addAttribute("sections", sections);
        model.addAttribute("subsections", subsections);
        return "allCheatsheets";
    }

    @GetMapping("/edit-cheatsheet/{id}")
    public String editCheatsheetPage(@PathVariable int id, Model model) {
        Cheatsheet cheatsheet = cheatsheetService.getCheatsheetById(id);
        List<Section> sections = sectionService.getAllSections();
        List<Subsection> subsections = subsectionService.getAllSubsections();
        List<BlockDTO> blockDTOList = cheatsheetService.getBlocksForCheatsheet(id);
        model.addAttribute("blocks", blockDTOList);
        model.addAttribute("cs", cheatsheet);
        model.addAttribute("sections", sections);
        model.addAttribute("subsections", subsections);
        return "editCheatsheet";
    }

    @GetMapping("/add-block")
    public String getAddBlockPage(@RequestParam("cheatsheet") int cheatsheet, @RequestParam("col") int col, Model model) {
        System.out.println(cheatsheet);
        System.out.println(col);
        model.addAttribute("cheatsheet", cheatsheet);
        model.addAttribute("col", col);
        return "addBlock";
    }

    @PostMapping("/add-block")
    public String saveBlock(@RequestParam("title") String title, @RequestParam("layout") String layout, @RequestParam("cheatsheet") int cheatsheetId, @RequestParam Map<String, String> params) {
        int result = cheatsheetService.addBlock(title, cheatsheetId, layout, params);
        if (result > 0) {
            return "redirect:/cheatsheets";
        } else {
            return "addBlock";
        }
    }
}
//    @PostMapping("/add-cheatsheet")
//    public String addCheatsheet(CheatsheetDTO cheatsheetDTO, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        UserOld currentUser = (UserOld) session.getAttribute("user");
////        User user = userRepository.getUserById(currentUser.getId());
//        int sectionId = cheatsheetDTO.getSectionId();
////        Section section = sectionRepository.getSectionById(sectionId);
//        int subsectionId = cheatsheetDTO.getSubsectionId();
////        Subsection subsection = subsectionRepository.getSubsectionById(subsectionId);
//        String name = cheatsheetDTO.getName();
//        String description = cheatsheetDTO.getDescription();
//        String color = cheatsheetDTO.getColor();
//        String style = cheatsheetDTO.getStyle();
//        String type = cheatsheetDTO.getType();
//        String language = cheatsheetDTO.getLanguage();
//        String layout = cheatsheetDTO.getLayout();
//        String title = request.getParameter("title");
//        StringBuilder contentBuilder = new StringBuilder();
//        contentBuilder.append("<table>");
//        contentBuilder.append("<tr><th>").append(title).append("</th><th></th></tr>");
//        int columnNumber = 1;
//        boolean isNewRow = true;
//        while (request.getParameter("column" + columnNumber) != null) {
//            if (isNewRow) {
//                contentBuilder.append("<tr>");
//                isNewRow = false;
//            }
//            String columnValue = request.getParameter("column" + columnNumber);
//            contentBuilder.append("<td>").append(columnValue).append("</td>");
//
//            if (columnNumber % 2 == 0) {
//                contentBuilder.append("</tr>");
//                isNewRow = true;
//            }
//            columnNumber++;
//        }
//        if (!isNewRow) {
//            contentBuilder.append("</tr>");
//        }
//        contentBuilder.append("</table>");
//        String content = contentBuilder.toString();
//        int result = cheatsheetRepository.save(name, description, color, content, style, type, language, layout, currentUser.getId(), sectionId, subsectionId);
//        if (result > 0) {
//            return "redirect:cheatsheets";
//        } else {
//            return "createCheatsheet";
//        }
//    }
