package com.example.catalog.mongo.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Catalog Exception")
public class CatalogException {
    @Schema(description = "error code")
    private final String message;
    @Schema(description = "throw")
    private final Throwable throwable;
    @Schema(description = "http status")
    private final HttpStatus httpStatus;

    public CatalogException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
