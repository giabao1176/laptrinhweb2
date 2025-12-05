package com.huy.controller;
import com.huy.entity.User;
import com.huy.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin/users") @RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private String checkLogin(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return null;
    }
    @GetMapping
    public String list(HttpSession session, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        model.addAttribute("page", service.search(keyword, page, size));
        model.addAttribute("keyword", keyword);
        return "user/list";
    }
    @GetMapping({"/add", "/edit/{id}"})
    public String form(HttpSession session, @PathVariable(required = false) Long id, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        User u = id == null ? new User() : service.findById(id);
        model.addAttribute("user", u);
        return "user/form";
    }
    @PostMapping("/save")
    public String save(HttpSession session, User user) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        service.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/delete/{id}")
    public String delete(HttpSession session, @PathVariable Long id) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        service.delete(id);
        return "redirect:/admin/users";
    }
}