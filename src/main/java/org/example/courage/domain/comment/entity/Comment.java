package org.example.courage.domain.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.entity.Board;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId; // 댓글 id

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    private MemberEntity userId; // 작성자 id

    private String content; // 내용

    private LocalDateTime createdDate; // 생성된 시간

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies; // 거래 내역 리스트

}
