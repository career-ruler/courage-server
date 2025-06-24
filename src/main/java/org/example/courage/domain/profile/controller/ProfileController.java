package org.example.courage.domain.profile.controller;

import lombok.RequiredArgsConstructor;

import org.example.courage.domain.auth.entity.MemberEntity;
import org.example.courage.domain.board.dto.GetBoardResponse;
import org.example.courage.domain.board.service.BoardQueryService;
import org.example.courage.domain.board.service.BoardService;
import org.example.courage.domain.profile.dto.ProfileResponse;
import org.example.courage.domain.profile.service.ProfileService;
import org.example.courage.global.auth.GetAuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final BoardService boardService;
    private final BoardQueryService boardQueryService;
    private final ProfileService profileService;


    // 프로필 조회 (GET /profile)
    @GetMapping
    public ResponseEntity<ProfileResponse> profile(@GetAuthenticatedUser MemberEntity member) {
        ProfileResponse response = profileService.getProfile(member);
        return ResponseEntity.ok(response);
    }

}
