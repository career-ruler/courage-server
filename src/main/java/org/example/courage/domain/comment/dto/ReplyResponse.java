package org.example.courage.domain.comment.dto;

import lombok.*;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.entity.Reply;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyResponse {

    private int replyId; // 대댓글 id
    private int commentId; // 댓글 id
//    private int boardId; // 게시글 id
    private String userId; // 작성자 id
    private String content; // 내용
    private LocalDateTime createdDate; // 생성된 시간


    public static ReplyResponse from(Reply reply) {
        return ReplyResponse.builder()
                .replyId(reply.getReplyId())
                .commentId(reply.getComment().getCommentId())
                .userId(reply.getUserId().getUserId())
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .build();
    }
}