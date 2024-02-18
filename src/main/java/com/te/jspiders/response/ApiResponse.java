package com.te.jspiders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class ApiResponse<T> {
    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String token;
    private T data;

    public ApiResponse(String message, String token, T data) {
        super();
        this.message = message;
        this.token = token;
        this.data = data;
    }

}