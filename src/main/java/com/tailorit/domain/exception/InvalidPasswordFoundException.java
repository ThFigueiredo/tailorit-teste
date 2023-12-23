package com.tailorit.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.time.Instant;

public class InvalidPasswordFoundException extends ErrorResponseException {
    public InvalidPasswordFoundException(String message) {
        super(HttpStatus.BAD_REQUEST, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setTitle("Invalid Password");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
