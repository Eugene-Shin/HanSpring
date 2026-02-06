package com.example.demo.dto.response;

import com.example.demo.exception.ErrorCode;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        String path,
        OffsetDateTime timestamp,
        List<FieldError> fieldErrors
) {
    public static ErrorResponse of(ErrorCode errorCode, String path) {
        return new ErrorResponse(
                errorCode.code(),
                errorCode.message(),
                path,
                OffsetDateTime.now(),
                List.of()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String path, String overrideMessage) {
        return new ErrorResponse(
                errorCode.code(),
                overrideMessage,
                path,
                OffsetDateTime.now(),
                List.of()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String path, List<FieldError> fieldErrors) {
        return new ErrorResponse(
                errorCode.code(),
                errorCode.message(),
                path,
                OffsetDateTime.now(),
                fieldErrors
        );
    }

    public record FieldError(String field, String reason) {}
}

