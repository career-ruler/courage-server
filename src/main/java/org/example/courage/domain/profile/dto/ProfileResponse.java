package org.example.courage.domain.profile.dto;

import lombok.*;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.profile.entity.Profile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  ProfileResponse {

    private String userId; // 사용자 아이디
    private List<GetBoardResponse> products; // 등록된 상품 목록

    public ProfileResponse(Profile profile, List<GetBoardResponse> products) {
        this.userId = profile.getUserId();
        this.products = products;

////        this.products = profile.getProduct().stream()
////                .map(product -> new GetProductResponse(product))
////                .collect(Collectors.toList());
    }
}
