package com.spring.controller;

import com.spring.dto.LoginDTO;
import com.spring.jbcrypt.BCrypt;
import com.spring.model.User;
import com.spring.repository.UserRepository;
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
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("loginPage", "user", new LoginDTO());
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") LoginDTO loginDTO, Model model, HttpServletRequest request) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        User userExist = userRepository.getUserByEmail(email);
        if (userExist == null) {
            model.addAttribute("error", "Email not existed!");
            model.addAttribute("email", email);
            return "loginPage";
        } else {
            String storedHashPassword = userExist.getPassword();
            Boolean passValid = BCrypt.checkpw(password, storedHashPassword);
            if (!passValid) {
                model.addAttribute("error", "Email or password wrong!");
                model.addAttribute("email", email);
                return "loginPage";
            }
            HttpSession session = request.getSession();
            User user = new User();
            user.setName(userExist.getName());
            user.setEmail(userExist.getEmail());
            user.setId(userExist.getId());
            user.setPassword(password);
            user.setDescription(userExist.getDescription());
            user.setWebsite(userExist.getWebsite());
            user.setRole(userExist.getRole());
//			userExist.setPassword(password);
            session.setAttribute("user", user);
            return "redirect:cheatsheets";
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
        return new ModelAndView("registerPage", "user", new User());
    }
}
