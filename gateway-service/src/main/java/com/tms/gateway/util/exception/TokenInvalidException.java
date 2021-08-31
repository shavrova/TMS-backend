package com.tms.gateway.util.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String error, HttpStatus internalServerError) {
    }
}
