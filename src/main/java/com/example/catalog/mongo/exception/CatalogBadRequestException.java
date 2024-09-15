package com.example.catalog.mongo.exception;

public class CatalogBadRequestException extends RuntimeException{
    public CatalogBadRequestException(String message) {
        super(message);
    }

    public CatalogBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}