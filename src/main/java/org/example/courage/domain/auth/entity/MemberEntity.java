package org.example.courage.domain.auth.entity;

import jakarta.persistence.*;
import lombok.*;
//import org.example.courage.domain.auth.MemberType;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

    @Id
    String userId; // 사용자 아이디

    @Column(nullable = false)
    String password; // 비밀번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType role;

}
