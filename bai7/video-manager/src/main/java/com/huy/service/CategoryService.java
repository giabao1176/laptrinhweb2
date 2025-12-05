package com.huy.service;
import com.huy.entity.Category;
import com.huy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service @RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;
    public Page<Category> search(String keyword, int page, int size) {
        Pageable p = PageRequest.of(page, size);
        if (keyword != null && !keyword.isBlank())
            return repo.findByCategorynameContainingIgnoreCase(keyword, p);
        return repo.findAll(p);
    }
    public List<Category> findAll() { return repo.findAll(); }
    public Category save(Category c) { return repo.save(c); }
    public Category findById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}