package com.duoc.proyecto.tienda_backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Mensaje personalizado para errores de restricción UNIQUE
        if (ex.getMessage() != null && ex.getMessage().contains("unique_nombre")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un producto con ese nombre");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de integridad de datos");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
