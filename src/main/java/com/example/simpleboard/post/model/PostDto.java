package com.example.simpleboard.post.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {
    private Long id;

    private Long boardId;

    private String userName;

    @JsonIgnore
    private String password;
    private String email;
    private String status;
    private String title;

    private String content;

    private LocalDateTime postedAt;
}
