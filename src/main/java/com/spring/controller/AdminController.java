package com.spring.controller;

import com.spring.dto.SubsectionDTO;
import com.spring.model.Section;
import com.spring.model.Subsection;
import com.spring.repository.SectionRepository;
import com.spring.repository.SubsectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubsectionRepository subsectionRepository;

    @GetMapping("/admin")
    public String getDashboardPage() {
        return "adminDashboard";
    }

    @GetMapping("/admin/section")
    public String getSectionPage(Model model) {
        List<Section> sections = sectionRepository.getAllSections();
        model.addAttribute("sections", sections);
        return "allSection";
    }

    @GetMapping("/admin/subsection")
    public String getSubsectionPage(Model model) {
        List<Subsection> subsections = subsectionRepository.getAllSubsections();
        model.addAttribute("subsections", subsections);
        return "allSubsection";
    }

    @GetMapping("/admin/add-section")
    public ModelAndView getAddSectionPage() {
        return new ModelAndView("addSection", "section", new Section());
    }

    @GetMapping("/admin/add-subsection")
    public ModelAndView getAddSubsectionPage(Model model) {
        List<Section> sections = sectionRepository.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("addSubsection", "subsectionDTO", new SubsectionDTO());
    }

    @PostMapping("/admin/add-section")
    public String addSection(Section section, Model model) {
        Section sectionExist = sectionRepository.getSectionByName(section.getName());
        if (sectionExist != null) {
            model.addAttribute("error", "Section name already existed");
            return "addSection";
        }
        int result = sectionRepository.save(section.getName());
        if (result > 0) {
            return "redirect:/admin/section";
        } else {
           return "addSection";
        }
    }

    @PostMapping("/admin/add-subsection")
    public String addSubsection(SubsectionDTO subsectionDTO, Model model) {
        List<Section> sections = sectionRepository.getAllSections();
        Subsection subsectionExist = subsectionRepository.getSubsectionByName(subsectionDTO.getName());
        if (subsectionExist != null) {
            model.addAttribute("error", "Subsection name already existed");
            model.addAttribute("sections", sections);
            return "addSubsection";
        } else {
            int result = subsectionRepository.save(subsectionDTO.getName(), subsectionDTO.getType(), subsectionDTO.getSectionId());
            if (result > 0) {
                return "redirect:/admin/subsection";
            } else {
                return "addSubsection";
            }
        }
    }
}
