package com.springboot.valid_exception.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.springboot.valid_exception") // 패키지 관제 경로 지정
public class CustomExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
//        HttpHeaders responseHeaders = new HttpHeaders();
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//        LOGGER.error("Advice 내 handlerException 호출, {}, {}", request.getRequestURI(), e.getMessage());
//
//        Map<String, String> map = new HashMap<>();
//        map.put("error type", status.getReasonPhrase());
//        map.put("code", "400");
//        map.put("message", e.getMessage());
//
//        return new ResponseEntity<>(map, responseHeaders, status);
//    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleException(CustomException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus status = e.getHttpStatus();

        LOGGER.error("Advice 내 handlerException 호출, {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("message", e.getMessage());


        return new ResponseEntity<>(map, responseHeaders, status);
    }
}
