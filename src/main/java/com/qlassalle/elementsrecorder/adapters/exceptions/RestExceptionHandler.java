package com.qlassalle.elementsrecorder.adapters.exceptions;

import com.qlassalle.elementsrecorder.domain.exceptions.EmailExistsException;
import com.qlassalle.elementsrecorder.domain.exceptions.InvalidPasswordException;
import com.qlassalle.elementsrecorder.domain.exceptions.UnauthorizedException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidPasswordException.class})
    public ResponseEntity<ApiResponse> inputException(InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> {
                     String fieldName = ((FieldError) error).getField();
                     String errorMessage = error.getDefaultMessage();
                     errors.put(fieldName, errorMessage);
                 });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiResponse> badCredentials(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("The provided password isn't correct"));
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ApiResponse> unauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<ApiResponse> emailExistsException(EmailExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(exception.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleUnexpectedException(Exception exception) {
        logger.error("Unhandled exception raised", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Unexpected error"));
    }

    @Value
    private static class ApiResponse {
        String message;
    }
}
