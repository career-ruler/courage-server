package org.example.courage.global;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponse<T> (
        int status,
        String message,
        T data
){}

