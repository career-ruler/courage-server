package org.example.courage.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshMemberRequest (

        @NotBlank
        String refreshToken

) { }
