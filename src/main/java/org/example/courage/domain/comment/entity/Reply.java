package org.example.courage.domain.comment.entity;

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
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId; // 대댓글 id

//    @ManyToOne
//    private Comment commentId; // 댓글 id

//    @ManyToOne
//    private Board boardId; // 게시글 id

    @ManyToOne
    private MemberEntity userId; // 작성자 id

    private String content; // 내용

    private LocalDateTime createdDate; // 생성된 시간

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;



}
