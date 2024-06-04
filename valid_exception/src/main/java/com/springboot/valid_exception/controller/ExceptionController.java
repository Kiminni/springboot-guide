package com.springboot.valid_exception.controller;

import com.springboot.valid_exception.common.Constants;
import com.springboot.valid_exception.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
    @GetMapping
    public void getRuntimeException() {
        throw new RuntimeException("RuntimeException 발생");
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        LOGGER.error("클래스 내 handlerException 호출, {}, {}", request.getRequestURI(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", status.getReasonPhrase());
        map.put("code", "400");
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, status);
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException {
    throw new CustomException(Constants.ExceptionClass.PRODUCT, "CustomException 발생", HttpStatus.BAD_REQUEST);
    }
}
