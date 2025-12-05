package com.huy.service;
import com.huy.entity.Video;
import com.huy.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class VideoService {
    private final VideoRepository repo;
    public Page<Video> search(String keyword, Long catId, int page, int size) {
        Pageable p = PageRequest.of(page, size);
        if (keyword != null && !keyword.isBlank() && catId != null)
            return repo.findByTitleContainingIgnoreCase(keyword, p);
        if (catId != null)
            return repo.findByCategoryCategoryId(catId, p);
        if (keyword != null && !keyword.isBlank())
            return repo.findByTitleContainingIgnoreCase(keyword, p);
        return repo.findAll(p);
    }
    public List<Video> findAll() { return repo.findAll(); }
    public Video save(Video v) { return repo.save(v); }
    public Video findById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}