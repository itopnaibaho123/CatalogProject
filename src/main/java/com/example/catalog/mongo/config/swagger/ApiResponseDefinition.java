package com.example.catalog.mongo.config.swagger;

import io.swagger.v3.oas.models.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDefinition<T> {
    private String description;
    private Schema schema;

    public ApiResponseDefinition(String description, Class<T> schemaClass) {
        this.description = description;
        this.schema = new Schema<T>().$ref("#/components/schemas/" + schemaClass.getSimpleName());
    }
}
