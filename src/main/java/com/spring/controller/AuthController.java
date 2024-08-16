package com.spring.controller;

import com.spring.dto.LoginDTO;
import com.spring.dto.ResponseDTO;
import com.spring.entity.User;
import com.spring.jbcrypt.BCrypt;
import com.spring.model.UserOld;
import com.spring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("loginPage", "user", new LoginDTO());
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") LoginDTO loginDTO, Model model, HttpServletRequest request) {
        ResponseDTO res = authService.loginUser(loginDTO);
        if(res.getStatus().equals("200")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", res.getUser());
            return "redirect:/cheatsheets";
        } else {
            model.addAttribute("error", res.getMessage());
            return "loginPage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("registerPage", "user", new UserOld());
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        ResponseDTO res = authService.registerUser(user);
        if(res.getStatus().equals("403")) {
            model.addAttribute("message", res.getMessage());
            return "registerPage";
        } else if (res.getStatus().equals("409")) {
            model.addAttribute("message", res.getMessage());
            return "registerPage";
        } else if (res.getStatus().equals("201")) {
            model.addAttribute("message", res.getMessage());
            return "redirect:/home";
        }  else {
            return "registerPage";
        }
    }
}
