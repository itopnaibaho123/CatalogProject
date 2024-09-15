package com.example.catalog.mongo.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "", description = "")
@ApiResponses({})
@Tag(name = "", description = "")
public @interface ApiDocumentation {

    String summary() default "";
    String description() default "";
    String tagName() default "";
    String tagDescription() default "";

    ApiResponse[] responses() default {};
}
