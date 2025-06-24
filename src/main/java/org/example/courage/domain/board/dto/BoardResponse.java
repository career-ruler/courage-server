package org.example.courage.domain.board.dto;

import lombok.*;
import org.example.courage.domain.board.entity.Category;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.comment.dto.CommentResponse;
import org.example.courage.domain.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponse {

    private int boardId; // 게시글 id
    private String userId; // 작성자 id
    private String title; // 제목
    private String description; // 내용
    private Optional<String> picture; // 사진
    private Category category; // 상품 종류
    private LocalDateTime createdDate; // 생성된 시간

    private List<CommentResponse> comments;
    //    private List<Reply> replies;

    public static BoardResponse from(Board board) {

        return BoardResponse.builder()
                .boardId(board.getBoardId())
                .userId(board.getUserId().getUserId())
                .title(board.getTitle())
                .description(board.getDescription())
                .picture(Optional.ofNullable(board.getPicture()))
                .category(board.getCategory())
                .createdDate(board.getCreatedDate())
                .build();
    }

}



