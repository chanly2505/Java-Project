package com.phoneshope.java.project.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@Data
@RequiredArgsConstructor
public class ApiException extends  RuntimeException {

    private final HttpStatus status;
    private final String message;
}