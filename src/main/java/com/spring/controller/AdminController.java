package com.spring.controller;

import com.spring.dto.ResponseDTO;
import com.spring.dto.SubsectionDTO;
import com.spring.entity.Section;
import com.spring.entity.Subsection;
import com.spring.repository.SectionRepository;
import com.spring.repository.SubsectionRepository;
import com.spring.service.SectionService;
import com.spring.service.SubsectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private SubsectionService subsectionService;

    @GetMapping("/admin")
    public String getDashboardPage() {
        return "adminDashboard";
    }

    @GetMapping("/admin/section")
    public String getSectionPage(Model model) {
        List<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return "allSection";
    }

    @GetMapping("/admin/subsection")
    public String getSubsectionPage(Model model) {
        List<Subsection> subsections = subsectionService.getAllSubsections();
        model.addAttribute("subsections", subsections);
        return "allSubsection";
    }

    @GetMapping("/admin/add-section")
    public ModelAndView getAddSectionPage() {
        return new ModelAndView("addSection", "section", new Section());
    }

    @GetMapping("/admin/subsection/{id}")
    public ModelAndView getEditSubsection(@PathVariable int id, Model model) {
        List<Section> sections = sectionService.getAllSections();
        Subsection subsection = subsectionService.getSubsectionById(id);
        model.addAttribute("sections", sections);
        return new ModelAndView("editSubsection", "subsection", subsection);
    }

    @PostMapping(("/admin/subsection/{id}"))
    public String updateSubsection(Subsection subsection, Model model) {
        ResponseDTO res = subsectionService.updateSubsection(subsection);
        if(res.getStatus().equals("200")) {
            return "redirect:/admin/subsection";
        } else {
            model.addAttribute("error", res.getMessage());
            return "redirect:/admin/subsection/{id}";
        }
    }

    @GetMapping("/admin/add-subsection")
    public ModelAndView getAddSubsectionPage(Model model) {
        List<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("addSubsection", "subsectionDTO", new SubsectionDTO());
    }

    @PostMapping("/admin/add-section")
    public String addSection(Section section, Model model) {
        Section sectionExist = sectionService.getSectionByName(section.getName());
        if (sectionExist != null) {
            model.addAttribute("error", "Section name already existed");
            return "addSection";
        }
        int result = sectionService.saveSection(section.getName());
        if (result > 0) {
            return "redirect:/admin/section";
        } else {
           return "addSection";
        }
    }

    @PostMapping("/admin/add-subsection")
    public String addSubsection(SubsectionDTO subsectionDTO, Model model) {
        List<Section> sections = sectionService.getAllSections();
        Subsection subsectionExist = subsectionService.getSubsectionByName(subsectionDTO.getName());
        if (subsectionExist != null) {
            model.addAttribute("error", "Subsection name already existed");
            model.addAttribute("sections", sections);
            return "addSubsection";
        } else {
            int result = subsectionService.saveSubsection(subsectionDTO.getName(), subsectionDTO.getType(), subsectionDTO.getSectionId());
            if (result > 0) {
                return "redirect:/admin/subsection";
            } else {
                return "addSubsection";
            }
        }
    }
}
