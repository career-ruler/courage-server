package org.example.courage.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final int status;
    private final String message;

    public static CustomException of(int status, String message) {
        return new CustomException(status, message);
    }

}
