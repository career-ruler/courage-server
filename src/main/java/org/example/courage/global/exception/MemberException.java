package org.example.courage.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberException {

    ALREADY_EXIST(CustomException.of(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 유저입니다")),
    NOT_EXIST(CustomException.of(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 유저입니다")),
    PASSWORD_NOT_MATCH(CustomException.of(HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다")),
    NOT_MATCH(CustomException.of(HttpStatus.BAD_REQUEST.value(), "인증번호가 일치하지 않습니다")),
    EXPIRED_NUMBER(CustomException.of(HttpStatus.BAD_REQUEST.value(), "인증번호 만료, 처음부터 다시 시도해주세요"));

    private final CustomException exception;

}