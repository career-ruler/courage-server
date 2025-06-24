package org.example.courage.global.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.courage.global.BaseResponse;
import org.example.courage.global.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final FilterErrorSender responser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException exception) {
            responser.send(response, exception);
        }
    }

    @ExceptionHandler(CustomException.class)
    public BaseResponse<?> handleCustomException(CustomException exception) {
        return new BaseResponse<>(exception.getStatus(), exception.getMessage(), null);
    }


}
