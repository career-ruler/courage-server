package org.example.courage.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.courage.global.BaseResponse;
import org.example.courage.global.exception.CustomException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FilterErrorSender {

    private final ObjectMapper mapper;

    public void send(HttpServletResponse response, CustomException exception) {
        int status = exception.getStatus();
        String message = exception.getMessage();

        try {
            response.setStatus(status);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(mapper.writeValueAsString(new BaseResponse<>(status, message, null)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
