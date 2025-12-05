package com.huy.controller;

import com.huy.entity.User;
import com.huy.service.UserService;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping({"/", "/login"})
    public String login(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/admin/videos";
        }
        return "login"; // ← Trả về login.html
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password,
                          HttpSession session, Model model) {
        User u = userService.findByUsername(username);
        if (u != null && u.getPassword().equals(password) && Boolean.TRUE.equals(u.getActive())) {
            session.setAttribute("user", u);
            return "redirect:/admin/videos";
        }
        model.addAttribute("error", "Sai username hoặc password!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}