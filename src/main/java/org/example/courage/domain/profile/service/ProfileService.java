package org.example.courage.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.example.courage.global.exception.MemberException;
import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.auth.repository.MemberRepository;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.entity.Board;
import org.example.courage.domain.board.repository.BoardRepository;
import org.example.courage.domain.profile.dto.ProfileResponse;
import org.example.courage.domain.profile.entity.Profile;
import org.example.courage.domain.profile.repository.ProfileRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository productRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public ProfileResponse getProfile(MemberEntity member) {
        MemberEntity entity = memberRepository.findByUserId(member.getUserId())
                .orElseThrow(MemberException.NOT_EXIST::getException);

        Profile profile = profileRepository.findByUserId(entity.getUserId());

        if (profile == null) {
            throw new RuntimeException("프로필이 존재하지 않습니다.");
        }

        List<Board> products = productRepository.findByUserIdOrderByCreatedDateDesc(entity.getUserId());
        List<GetBoardResponse> productResponses = products.stream()
                .map(GetBoardResponse::from)
                .toList();


        return new ProfileResponse(profile, productResponses);

    }
}
