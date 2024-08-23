package com.spring.controller;

import com.spring.dto.ResponseDTO;
import com.spring.dto.SubsectionDTO;
import com.spring.entity.Section;
import com.spring.entity.Subsection;
import com.spring.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private SubsectionService subsectionService;

    @Autowired
    private HttpServletRequest request;

    public Boolean isAdmin() {
        boolean isAdmin = false;
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if(loginUser != null) {
            if(loginUser.getRole().name().equals("ADMIN")) {
                return true;
            } else {
                return false;
            }
        }
        return isAdmin;
    }

    @GetMapping("/admin")
    public String getDashboardPage() {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        return "adminDashboard";
    }

    @GetMapping("/admin/section")
    public String getSectionPage(Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        List<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return "allSection";
    }

    @GetMapping("/admin/subsection")
    public String getSubsectionPage(Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        List<Subsection> subsections = subsectionService.getAllSubsections();
        model.addAttribute("subsections", subsections);
        return "allSubsection";
    }

    @GetMapping("/subsection/delete/{id}")
    public String deleteSubsection(@PathVariable int id) {
        ResponseDTO res = subsectionService.deleteById(id);
        if(!res.getStatus().equals("200")) {
            return "redirect:/admin/subsection";
        }
        return "redirect:/admin/subsection";
    }

    @GetMapping("/section/delete/{id}")
    public String deleteSection(@PathVariable int id) {
        ResponseDTO res = sectionService.deleteById(id);
        if(!res.getStatus().equals("200")) {
            return "redirect:/admin/section";
        }
        return "redirect:/admin/section";
    }

    @GetMapping("/admin/add-section")
    public Object getAddSectionPage() {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        return new ModelAndView("addSection", "section", new Section());
    }

    @GetMapping("/admin/section/{id}")
    public Object getEditSection(@PathVariable int id, Model model) {
//        boolean isAdmin = isAdmin();
//        if(!isAdmin) {
//            return "redirect:/";
//        }
        Section section = sectionService.getSectionById(id);
        return new ModelAndView("editSection", "section", section);
    }

    @GetMapping("/admin/subsection/{id}")
    public Object getEditSubsection(@PathVariable int id, Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        List<Section> sections = sectionService.getAllSections();
        Subsection subsection = subsectionService.getSubsectionById(id);
        model.addAttribute("sections", sections);
        return new ModelAndView("editSubsection", "subsection", subsection);
    }

    @PostMapping(("/admin/section/{id}"))
    public Object updateSection(Section section, Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        ResponseDTO res = sectionService.updateSection(section);
        if(res.getStatus().equals("200")) {
            return "redirect:/admin/section";
        } else {
            model.addAttribute("error", res.getMessage());
            return new ModelAndView("editSection", "section", section);
        }
    }

    @PostMapping(("/admin/subsection/{id}"))
    public String updateSubsection(Subsection subsection, Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        ResponseDTO res = subsectionService.updateSubsection(subsection);
        if(res.getStatus().equals("200")) {
            return "redirect:/admin/subsection";
        } else {
            model.addAttribute("error", res.getMessage());
            return "redirect:/admin/subsection/{id}";
        }
    }

    @GetMapping("/admin/add-subsection")
    public Object getAddSubsectionPage(Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
        List<Section> sections = sectionService.getAllSections();
        model.addAttribute("sections", sections);
        return new ModelAndView("addSubsection", "subsectionDTO", new SubsectionDTO());
    }

    @PostMapping("/admin/add-section")
    public String addSection(Section section, Model model) {
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
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
        boolean isAdmin = isAdmin();
        if(!isAdmin) {
            return "redirect:/";
        }
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
