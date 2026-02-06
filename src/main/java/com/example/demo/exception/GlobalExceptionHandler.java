package com.example.demo.exception;

import com.example.demo.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(
            BusinessException e,
            HttpServletRequest request
    ) {
        ErrorCode code = e.getErrorCode();
        String path = request.getRequestURI();

        // BusinessException에서 overrideMessage를 쓰고 싶으면 e.getMessage()를 사용
        ErrorResponse body = ErrorResponse.of(code, path, e.getMessage());
        return ResponseEntity.status(code.status()).body(body);
    }

    // @Valid 검증 실패(주로 @RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        String path = request.getRequestURI();

        List<ErrorResponse.FieldError> fieldErrors =
                e.getBindingResult().getFieldErrors().stream()
                        .map(fe -> new ErrorResponse.FieldError(fe.getField(), fe.getDefaultMessage()))
                        .toList();

        ErrorResponse body = ErrorResponse.of(ErrorCode.INVALID_REQUEST, path, fieldErrors);
        return ResponseEntity.status(ErrorCode.INVALID_REQUEST.status()).body(body);
    }

    // 바인딩 실패(주로 @ModelAttribute / @RequestParam)
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorResponse> handleBind(
            BindException e,
            HttpServletRequest request
    ) {
        String path = request.getRequestURI();

        List<ErrorResponse.FieldError> fieldErrors =
                e.getBindingResult().getFieldErrors().stream()
                        .map(fe -> new ErrorResponse.FieldError(fe.getField(), fe.getDefaultMessage()))
                        .toList();

        ErrorResponse body = ErrorResponse.of(ErrorCode.INVALID_REQUEST, path, fieldErrors);
        return ResponseEntity.status(ErrorCode.INVALID_REQUEST.status()).body(body);
    }

    // 파라미터 타입 미스매치 (예: Long 자리에 "abc")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException e,
            HttpServletRequest request
    ) {
        String path = request.getRequestURI();
        String msg = "요청 파라미터 타입이 올바르지 않습니다. name=" + e.getName() + ", value=" + e.getValue();
        log.error(msg + " && endpoint : " + path);
        ErrorResponse body = ErrorResponse.of(ErrorCode.INVALID_REQUEST, path, msg);
        return ResponseEntity.status(ErrorCode.INVALID_REQUEST.status()).body(body);
    }

    // 최후의 보루
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(
            Exception e,
            HttpServletRequest request
    ) {
        String path = request.getRequestURI();
        ErrorResponse body = ErrorResponse.of(ErrorCode.INTERNAL_ERROR, path);
        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.status()).body(body);
    }
}