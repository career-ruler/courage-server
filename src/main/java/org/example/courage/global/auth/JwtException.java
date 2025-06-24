package org.example.courage.global.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.courage.global.exception.CustomException;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtException {
    EXPIRED(CustomException.of(HttpStatus.UNAUTHORIZED.value(), "토큰이 만료되었어요")),
    SIGNATURE(CustomException.of(HttpStatus.UNAUTHORIZED.value(), "토큰의 서명이 일치하지 않아요")),
    MALFORMED(CustomException.of(HttpStatus.UNAUTHORIZED.value(), "토큰이 구조가 이상하거나 데이터가 일치하지 않아요")),
    UNSUPPORTED(CustomException.of(HttpStatus.UNAUTHORIZED.value(), "지원하지 않는 토큰이에요")),
    ILLEGAL_ARGUMENT(CustomException.of(HttpStatus.UNAUTHORIZED.value(), "JWT 처리 과정에서 오류가 발생했어요"));

    private final CustomException exception;
}
