package com.spring.controller;

import com.spring.dto.ResponseDTO;
import com.spring.entity.User;
import com.spring.service.UserService;
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

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile/{id}")
    public String getMyProfilePage(@PathVariable int id, Model model, HttpServletRequest request) {
        User user = userService.getUserById(id);
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if(loginUser != null) {
            if(user != null && user.getId() == loginUser.getId()) {
                model.addAttribute("user", user);
                return "myProfile";
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView getUserEditPage(@PathVariable int id) {
        User user = userService.getUserById(id);
        if(user != null) {
            return new ModelAndView("editProfile", "user", user);
        } else {
            return new ModelAndView("registerPage", "user", new User());
        }
    }

    @PostMapping("/edit-user/{id}")
    public String updateUser(@PathVariable int id, User user) {
        int result = 0;
        result = userService.updateUser(user);
        if(result > 0) {
            return "redirect:/profile/{id}";
        } else {
            return "editProfile";
        }
    }

    @GetMapping("/change-password/{id}")
    public String getChangePasswordPage(@PathVariable int id, HttpServletRequest request) {
        User user = userService.getUserById(id);
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if(loginUser == null) {
            return "redirect:/home";
        } else {
            return "changePassword";
        }
    }

    @PostMapping("/change-password/{id}")
    public String changePassword(@PathVariable int id, Model model, HttpServletRequest request, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
        ResponseDTO res = null;
        User user = userService.getUserById(id);
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if(loginUser == null) {
            return "redirect:/home";
        } else {
            res = userService.changePassword(id, oldPassword, newPassword, confirmPassword);
            if(!res.getStatus().equals("200")) {
                model.addAttribute("error", res.getMessage());
                return "changePassword";
            } else {
                return "redirect:/profile/{id}";
            }
        }
    }
}
