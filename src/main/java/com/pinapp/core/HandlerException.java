package com.pinapp.core;

import com.pinapp.model.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class HandlerException {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public @ResponseBody
    ResponseEntity<Object> handleServiceNotFoundException(final IllegalArgumentException ex) {
        log.error("Error de datos no encontrados {}", ex.getMessage());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), errorResponseDTO.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody
    ResponseEntity<Object> handleErrorException(final Exception ex) {
        log.error("Error de servicio");
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(ex.getMessage()).build();
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), errorResponseDTO.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public @ResponseBody
    ResponseEntity<Object> handleErrorArgumentNotValid(final MethodArgumentNotValidException ex) {
        log.error("Objeto invalido {} error en el campo {}", ex.getFieldError().getObjectName(), ex.getFieldError().getField());
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().status(HttpStatus.BAD_REQUEST).message(ex.getFieldError().getDefaultMessage()).build();
        return new ResponseEntity<>(errorResponseDTO, new HttpHeaders(), errorResponseDTO.getStatus());
    }


}