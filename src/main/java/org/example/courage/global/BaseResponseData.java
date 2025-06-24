package org.example.courage.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponseData<T> extends BaseResponseParent {

    private final T data;

    private BaseResponseData(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

}