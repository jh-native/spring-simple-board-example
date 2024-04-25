package com.example.simpleboard.post.db;

import com.example.simpleboard.reply.db.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    // ~Entity.java 파일은 모드 DB Table Column으로 인식함 -> 예외처리
    @Transient
    private List<ReplyEntity> replyList = List.of();
}
