package com.springboot.valid_exception.controller;

import com.springboot.valid_exception.data.dto.ValidRequestDto;
import com.springboot.valid_exception.data.dto.ValidationRequestDto;
import com.springboot.valid_exception.data.group.ValidationGroup1;
import com.springboot.valid_exception.data.group.ValidationGroup2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class ValidationController {

    private final Logger LOGGER = Logger.getLogger(ValidationController.class.getName());

    @PostMapping("/valid")
    public ResponseEntity<String> checkvalidationByValid(@Valid @RequestBody ValidRequestDto validRequestDto) {
        LOGGER.info(validRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
    }

    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation(@Validated @RequestBody ValidationRequestDto validationRequestDto) {
        LOGGER.info(validationRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validationRequestDto.toString());
    }

    @PostMapping("/validated/group1")
    public ResponseEntity<String> checkValidation1(
            @Validated(ValidationGroup1.class) @RequestBody ValidationRequestDto validationRequestDto) {
        LOGGER.info(validationRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validationRequestDto.toString());
    }

    @PostMapping("/validated/group2")
    public ResponseEntity<String> checkValidation2(
            @Validated(ValidationGroup2.class) @RequestBody ValidationRequestDto validationRequestDto) {
        LOGGER.info(validationRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validationRequestDto.toString());
    }

    @PostMapping("/validated/all-group")
    public ResponseEntity<String> checkValidation3(
            @Validated({ValidationGroup1.class, ValidationGroup2.class}) @RequestBody ValidationRequestDto validationRequestDto) {
            LOGGER.info(validationRequestDto.toString());
            return ResponseEntity.status(HttpStatus.OK).body(validationRequestDto.toString());
        }
}
