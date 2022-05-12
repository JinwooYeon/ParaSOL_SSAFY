package com.parasol.BaaS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({
            MethodArgumentNotValidException.class, // 유효하지 않은 인수 값
    })
    public ResponseEntity<Object> InvalidRequestException(final MethodArgumentNotValidException ex) {
        log.error("Invalid Request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler({
            IllegalArgumentException.class // 유효하지 않은 인수 값 또는 필요한 인수가 들어오지 않았을 때
    })
    public ResponseEntity<Object> InsufficientRequestException(final IllegalArgumentException ex) {
        log.error("Insufficient Request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler({
            AccessDeniedException.class // 로그인이 필요한 페이지에 로그인 없이 접근
    })
    public ResponseEntity<Object> AccessDeniedException(final AccessDeniedException ex) {
        log.error("Access Denied", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler({
            IllegalAccessException.class // 일정 권한이 필요한 페이지에 권한 없이 접근
    })
    public ResponseEntity<Object> ForbiddenException(final IllegalAccessException ex) {
        log.error("Forbidden", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler({
            Exception.class // 기타 모든 오류
    })
    public ResponseEntity<Object> InternalServerErrorException(final Exception ex) {
        log.error("Internal Server Error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
