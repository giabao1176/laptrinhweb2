package com.huy.controller.api;

import com.huy.entity.Video;
import com.huy.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // ← DÒNG NÀY SIÊU QUAN TRỌNG – CHO PHÉP AJAX GỌI KHI CHƯA LOGIN
public class VideoApiController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getVideos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId) {

        Page<Video> result = videoService.search(keyword, categoryId, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        videoService.delete(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}