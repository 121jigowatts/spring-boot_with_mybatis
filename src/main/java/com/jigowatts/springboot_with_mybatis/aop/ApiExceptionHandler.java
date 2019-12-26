package com.jigowatts.springboot_with_mybatis.aop;

import com.jigowatts.springboot_with_mybatis.controller.ApiError;
import com.jigowatts.springboot_with_mybatis.controller.ResourceNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        ApiError apiError = createApiError(ex);
        return super.handleExceptionInternal(ex, apiError, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleMyException(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = createApiError(ex);
        return super.handleExceptionInternal(ex, apiError, null, HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError apiError = createApiError(ex);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ApiError createApiError(Exception ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage(ex.getMessage());
        apiError.setDocumentationUrl("http://example.com/api/errors");
        return apiError;
    }
}