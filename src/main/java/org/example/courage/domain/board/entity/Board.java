package org.example.courage.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.BoardRequest;
import org.example.courage.domain.comment.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId; // 상품 id

    @ManyToOne
    private MemberEntity userId; // 작성자 id

    private String title; // 상품 제목

    private String description; // 상품 설명

    private String picture; // 상품 사진

    private Category category; // 상품 종류

    private LocalDateTime createdDate; // 생성된 시간

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments;


//    @ManyToOne
//    private Profile profile;

    public Board(BoardRequest productRequest) {
        this.title = productRequest.getTitle();
        this.description = productRequest.getDescription();
        this.category = productRequest.getCategory();
        this.picture = productRequest.getPicture() != null ? productRequest.getPicture().getOriginalFilename() : null;

    }

}
