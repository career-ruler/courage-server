package org.example.courage.domain.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.courage.domain.board.entity.Board;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {

    @Id
    @Column(nullable = false, unique = true)
    private String userId; // 사용자 아이디

    @OneToMany
//    @JoinColumn(name = "product_id", nullable = false)
    private List<Board> product;

}
