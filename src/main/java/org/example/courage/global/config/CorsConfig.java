package org.example.courage.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class
CorsConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "https://port-0-haso-server-m70dmespb703c228.sel4.cloudtype.app",
                "http://localhost:8345",
                "http://localhost:3000"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // 허용할 헤더
        config.setAllowedHeaders(List.of("Content-Type", "Authorization"));

        // 응답 헤더 노출 허용 (필요한 경우)
        config.setExposedHeaders(List.of("Authorization"));

        // 인증 정보 포함 허용
        config.setAllowCredentials(true);

        // 모든 경로에 대해 CORS 적용
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
