package org.example.courage.domain.auth.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public record SignupMemberRequest (

        @NotBlank(message = "userId는 공백일 수 없습니다")
        String userId,

        @NotBlank(message = "password는 공백일 수 없습니다")
        String password

) {

}
