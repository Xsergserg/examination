package com.example.gerimedicaexam.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ DefaultCustomException.class, RecordNotFoundException.class, ParsingException.class, MultipartException.class})
    public final ResponseEntity<ApiError> handleException(Throwable ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof RecordNotFoundException)
            status = HttpStatus.NOT_FOUND;
        if (ex instanceof MultipartException)
            return getMultipartExceptionResponse(ex, status);
        return getExceptionResponse(ex, status);
    }
    private ResponseEntity<ApiError> getExceptionResponse(Throwable ex, HttpStatus status) {
        return new ResponseEntity<>(new ApiError(status, ex.getCause().getMessage(), ex.getMessage()), status);
    }

    private ResponseEntity<ApiError> getMultipartExceptionResponse(Throwable ex, HttpStatus status) {
        return new ResponseEntity<>(new ApiError(status, ((MultipartException)ex).getMostSpecificCause().getMessage(), ex.getMessage()), status);
    }
}
