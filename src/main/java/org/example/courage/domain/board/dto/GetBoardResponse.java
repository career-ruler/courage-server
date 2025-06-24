package org.example.courage.domain.board.dto;

import lombok.*;
import org.example.courage.domain.board.entity.Category;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.comment.entity.Comment;
import org.example.courage.domain.comment.entity.Reply;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBoardResponse {

    private int boardId; // 상품 id
    private String title; // 상품 제목
    private String picture; // 상품 사진
    private Category category; // 상품 종류
    private LocalDateTime createdDate; // 생성된 시간


    public static GetBoardResponse from(Board product) {
        return GetBoardResponse.builder()
                .boardId(product.getBoardId())
                .title(product.getTitle())
                .picture(product.getPicture())
                .category(product.getCategory())
                .createdDate(product.getCreatedDate())
                .build();
    }

}
