package com.qlassalle.elementsrecorder.exceptions;

import com.qlassalle.elementsrecorder.model.output.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> badCredentials(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("Wrong credentials"));
    }

    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<Object> emailExistsException(EmailExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ApiResponse> unauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception exception) {
        logger.error("Unhandled exception raised", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unexpected error"));
    }
}
