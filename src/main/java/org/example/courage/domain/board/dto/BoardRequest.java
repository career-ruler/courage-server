package org.example.courage.domain.board.dto;

import lombok.*;
import org.example.courage.domain.board.entity.Category;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequest {

    private String title; // 제목
    private String description; // 내용
    private MultipartFile picture; // 사진
    private Category category; // 태그


}
