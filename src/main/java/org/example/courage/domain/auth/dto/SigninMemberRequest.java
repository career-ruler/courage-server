package org.example.courage.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record SigninMemberRequest (

        @NotBlank(message = "userId는 공백일 수 없습니다")
        String userId,

        @NotBlank(message = "password는 공백일 수 없습니다")
        String password

) { }