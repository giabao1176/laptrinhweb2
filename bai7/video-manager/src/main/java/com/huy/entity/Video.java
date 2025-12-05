package com.huy.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "Video") @Data @NoArgsConstructor @AllArgsConstructor
public class Video {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;
    private String title;
    private String poster;
    private Integer views = 0;
    private String description;
    private Boolean active = true;
    @ManyToOne @JoinColumn(name = "categoryId")
    private Category category;
}