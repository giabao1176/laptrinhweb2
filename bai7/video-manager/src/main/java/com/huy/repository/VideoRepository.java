package com.huy.repository;
import com.huy.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByTitleContainingIgnoreCase(String keyword, Pageable p);
    Page<Video> findByCategoryCategoryId(Long catId, Pageable p);
}