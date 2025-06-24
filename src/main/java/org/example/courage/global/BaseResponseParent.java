package org.example.courage.global;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class BaseResponseParent {

    private int status;
    private String message;

    public static BaseResponseParent ok(String message) {
        return new BaseResponseParent(HttpStatus.OK.value(), message);
    }

}