package com.dti.tictactoe.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TicTacToeExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UnhealthyDataException.class})
    public ResponseEntity<Object> handleUnhealtyDataException(UnhealthyDataException ex, WebRequest request) {
        return handleExceptionInternal(ex, new Error(ex.getError()), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    public static class Error {
        private String userMessage;
        private LocalDateTime timestamp;

        public Error(String userMessage) {
            this.userMessage = userMessage;
            this.timestamp = LocalDateTime.now();
        }

        public String getUserMessage() {
            return userMessage;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}
