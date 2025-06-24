package org.example.courage.global.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

        @NotBlank
        private String secretKey;

        @Positive
        @NotBlank
        private long accessExpire;

        @Positive
        @NotBlank
        private long refreshExpire;

}
