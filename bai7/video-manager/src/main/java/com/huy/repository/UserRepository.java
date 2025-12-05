package com.huy.repository;
import com.huy.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByFullnameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String k1, String k2, Pageable p);
    Optional<User> findByUsername(String username);
}