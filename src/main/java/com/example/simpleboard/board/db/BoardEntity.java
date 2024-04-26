package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLOrder;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")

public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;
    private String status;

    // 1:N 연결 설정
    @OneToMany(
            mappedBy = "board"
    )
    @Builder.Default
    // @Where // hibernate 6.3 부터 Deprecated 됨
    // @Where(clause = "status = 'REGISTERED'")
    @SQLRestriction("status = 'REGISTERED'")
    // @OrderBy도 Deprecated 됨
    @SQLOrder(value = "id desc")
    private List<PostEntity> postList = List.of();
}
