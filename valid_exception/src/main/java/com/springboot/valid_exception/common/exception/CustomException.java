package com.springboot.valid_exception.common.exception;

import com.springboot.valid_exception.common.Constants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception{

    private final Constants.ExceptionClass exceptionClass;
    private final HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass, String message, HttpStatus httpStatus) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }
    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
