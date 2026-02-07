package com.assessment.part2.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
    Kode Exception
 -> 99
    VALIDATION      = 01
    DATABASE        = 02
    AUTH            = 03
    MEDIA / FILE    = 04
    EXTERNAL API    = 05
    OTHER           = 99
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidJson(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                buildErrorResponse(
                        "Invalid JSON Format!!",
                        "X9901"
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                buildErrorResponse(
                        ex.getMessage(),
                        "X9902"
                ),
                HttpStatus.BAD_REQUEST
        );
    }


    private ErrorResponse buildErrorResponse(String message, String code) {
        return new ErrorResponse(message, code);
    }

    static class ErrorResponse {
        private String message;
        private String code;

        public ErrorResponse(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }
}
