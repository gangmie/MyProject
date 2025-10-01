package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email", nullable = false)
    private String memberEmail;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    private String name;   // 작성자 닉네임
    private String title;  // 메모 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 메모 내용

    @PrePersist
    public void prePersist() {
        this.modDate = LocalDateTime.now();
    }
}
