package com.entyxe.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ApiError {
    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
