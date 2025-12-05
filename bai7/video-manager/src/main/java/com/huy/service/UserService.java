package com.huy.service;
import com.huy.entity.User;
import com.huy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    public Page<User> search(String keyword, int page, int size) {
        Pageable p = PageRequest.of(page, size);
        if (keyword != null && !keyword.isBlank())
            return repo.findByFullnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(keyword, keyword, p);
        return repo.findAll(p);
    }
    public User findByUsername(String username) { return repo.findByUsername(username).orElse(null); }
    public User save(User u) { return repo.save(u); }
    public User findById(Long id) { return repo.findById(id).orElse(null); }
    public void delete(Long id) { repo.deleteById(id); }
}