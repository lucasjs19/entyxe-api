package com.entyxe.controller.handler;

import com.entyxe.dto.error.ApiError;
import com.entyxe.exception.BusinessException;
import com.entyxe.exception.ClienteNotFoundException;
import com.entyxe.exception.EnderecoNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> buildError(
            HttpStatus status,
            String message,
            HttpServletRequest request
    ){
        ApiError error = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                LocalDateTime.now()

        );
        System.out.println("API ERROR: " + error.toString());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ApiError> handleClienteNotFound(
            ClienteNotFoundException e,
            HttpServletRequest request
    ){
        return buildError(HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<ApiError> handleEnderecoNotFound(
            EnderecoNotFoundException e,
            HttpServletRequest request) {

        return buildError(HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusinessException(
            BusinessException e,
            HttpServletRequest request) {

        return buildError(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(ex -> ex.getField() + ": " + ex.getDefaultMessage())
                .orElse("Erro de validação");

        return buildError(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleBodyInvalido(
            HttpMessageNotReadableException e,
            HttpServletRequest request
    ) {
        return buildError(
                HttpStatus.BAD_REQUEST,
                "Corpo da requisição inválido ou ausente",
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(
            Exception e,
            HttpServletRequest request
    ){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado", request);
    }
}
