package com.jigowatts.springboot_with_mybatis.aop;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jigowatts.springboot_with_mybatis.presentation.controller.ApiError;
import com.jigowatts.springboot_with_mybatis.presentation.controller.NotFoundException;
import com.jigowatts.springboot_with_mybatis.presentation.controller.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    private static final Map<Class<? extends Exception>, String> messageMappings = new LinkedHashMap<>();

    static {
        messageMappings.put(HttpMessageNotReadableException.class, "Request body is invalid.");
        messageMappings.put(MethodArgumentNotValidException.class, "Request value is invalid.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ApiError apiError = createApiError(ex, "System error is occurred.");
        return super.handleExceptionInternal(ex, apiError, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleMyException(NotFoundException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleMyException(ResourceNotFoundException ex, WebRequest request) {
        return this.handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError apiError = createApiError(ex, ex.getMessage());
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = createApiError(ex, ex.getMessage());
        ex.getBindingResult().getGlobalErrors().stream()
                .forEach(e -> apiError.addDetail(e.getObjectName(), getMessage(e, request)));
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(e -> apiError.addDetail(e.getField(), getMessage(e, request)));
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private String getMessage(MessageSourceResolvable resolvable, WebRequest request) {
        return messageSource.getMessage(resolvable, request.getLocale());
    }

    private ApiError createApiError(Exception ex, String defaultMessage) {
        ApiError apiError = new ApiError();
        apiError.setMessage(resolveMessage(ex, defaultMessage));
        apiError.setDocumentationUrl("http://example.com/api/errors");
        return apiError;
    }

    private String resolveMessage(Exception ex, String defaultMessage) {
        return messageMappings.entrySet().stream().filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
                .findFirst().map(Map.Entry::getValue).orElse(defaultMessage);
    }

}