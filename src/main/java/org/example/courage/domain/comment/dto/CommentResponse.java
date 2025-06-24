package org.example.courage.domain.comment.dto;

import lombok.*;
import org.example.courage.domain.board.dto.BoardResponse;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private int commentId; // 댓글 id
    private int boardId; // 게시글 id
    private String userId; // 작성자 id
    private String content; // 내용
    private LocalDateTime createdDate; // 생성된 시간

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .boardId(comment.getBoard().getBoardId())
                .userId(comment.getUserId().getUserId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .build();
    }


}