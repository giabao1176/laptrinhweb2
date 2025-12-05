package com.huy.controller;
import com.huy.entity.Category;
import com.huy.service.CategoryService;
import com.huy.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin/categories") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryService catService;
    private final UserService userService;
    private String checkLogin(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return null;
    }
    @GetMapping
    public String list(HttpSession session, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        model.addAttribute("page", catService.search(keyword, page, size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("users", userService.search(null, 0, 100).getContent());
        return "category/list";
    }
    @GetMapping({"/add", "/edit/{id}"})
    public String form(HttpSession session, @PathVariable(required = false) Long id, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        Category c = id == null ? new Category() : catService.findById(id);
        model.addAttribute("category", c);
        model.addAttribute("users", userService.search(null, 0, 100).getContent());
        return "category/form";
    }
    @PostMapping("/save")
    public String save(HttpSession session, Category category) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        catService.save(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/delete/{id}")
    public String delete(HttpSession session, @PathVariable Long id) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        catService.delete(id);
        return "redirect:/admin/categories";
    }
}