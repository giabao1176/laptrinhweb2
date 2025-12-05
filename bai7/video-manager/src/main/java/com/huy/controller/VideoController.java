package com.huy.controller;
import com.huy.entity.Video;
import com.huy.service.CategoryService;
import com.huy.service.VideoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/admin/videos") @RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    private final CategoryService catService;
    private String checkLogin(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return null;
    }
    @GetMapping
    public String list(HttpSession session, @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false) Long categoryId, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        model.addAttribute("page", videoService.search(keyword, categoryId, page, size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", catService.findAll());
        return "video/list";
    }
    @GetMapping({"/add", "/edit/{id}"})
    public String form(HttpSession session, @PathVariable(required = false) Long id, Model model) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        Video v = id == null ? new Video() : videoService.findById(id);
        model.addAttribute("video", v);
        model.addAttribute("categories", catService.findAll());
        return "video/form";
    }
    @PostMapping("/save")
    public String save(HttpSession session, Video video) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        videoService.save(video);
        return "redirect:/admin/videos";
    }
    @GetMapping("/delete/{id}")
    public String delete(HttpSession session, @PathVariable Long id) {
        String redirect = checkLogin(session); if (redirect != null) return redirect;
        videoService.delete(id);
        return "redirect:/admin/videos";
    }
}