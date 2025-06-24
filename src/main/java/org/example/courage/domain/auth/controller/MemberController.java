package org.example.courage.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.courage.domain.auth.dto.RefreshMemberRequest;
import org.example.courage.domain.auth.dto.SigninMemberRequest;
import org.example.courage.domain.auth.dto.SignupMemberRequest;
import org.example.courage.domain.auth.service.MemberService;
import org.example.courage.global.BaseResponse;
import org.example.courage.global.auth.TokenInfo;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<TokenInfo>> createMember(@Valid @RequestBody SignupMemberRequest dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            BaseResponse<TokenInfo> response = new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        BaseResponse<TokenInfo> response = new BaseResponse<>(
                HttpStatus.OK.value(),
                "회원가입을 완료하였습니다",
                memberService.signupMember(dto)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/signin")
    public BaseResponse<TokenInfo> getMember(@RequestBody SigninMemberRequest dto) {
        return new BaseResponse<>(
                HttpStatus.OK.value(),
                "로그인 완료",
                memberService.signinMember(dto)
        );
    }

    @PostMapping("/refresh")
    public BaseResponse<String> refreshMember(@RequestBody RefreshMemberRequest dto) {
        return new BaseResponse<>(
                HttpStatus.OK.value(),
                "refresh 완료",
                memberService.refreshMember(dto)
        );
    }

    @DeleteMapping("/signout")
    public BaseResponse<Void> deleteMember(@RequestBody SigninMemberRequest dto) {
        memberService.deleteMember(dto);
        return new BaseResponse<>(HttpStatus.OK.value(), "회원 탈퇴 완료", null);
    }

}
