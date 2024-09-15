package com.example.catalog.mongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CatalogExceptionHandler {

    @ExceptionHandler(value = {CatalogNotFoundException.class})
    public ResponseEntity<Object> NotFoundException
            (CatalogNotFoundException catalogNotFoundException)
    {
        CatalogException catalogException = new CatalogException(
                catalogNotFoundException.getMessage(),
                catalogNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(catalogException , HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {CatalogBadRequestException.class})
    public ResponseEntity<Object> BadRequest
            (CatalogNotFoundException catalogNotFoundException)
    {
        CatalogException catalogException = new CatalogException(
                catalogNotFoundException.getMessage(),
                catalogNotFoundException.getCause(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(catalogException , HttpStatus.BAD_REQUEST);
    }
}
