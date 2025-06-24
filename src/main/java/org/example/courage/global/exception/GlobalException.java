package org.example.courage.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalException {

    SERVER_ERROR(CustomException.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 오류가 발생하였습니다"));

    private final CustomException exception;

}
