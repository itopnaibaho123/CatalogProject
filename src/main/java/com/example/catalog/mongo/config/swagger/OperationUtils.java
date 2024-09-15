package com.example.catalog.mongo.config.swagger;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.List;
import java.util.Map;

public class OperationUtils {

    public static Operation createOperation(
            String summary,
            String description,
            List<Parameter> parameters,
            Map<String, ApiResponseDefinition> responseDefinitions,
            RequestBody requestBody,
            boolean isGetOperation,
            boolean isPostOperation,
            boolean isPutOperation,
            boolean isDeleteOperation) {

        Operation operation = new Operation()
                .summary(summary)
                .description(description)
                .parameters(parameters)
                .responses(createResponses(responseDefinitions));

        if (isPostOperation || isPutOperation && requestBody != null) {
            operation.requestBody(requestBody);
        }

        return operation;
    }

    private static ApiResponses createResponses(Map<String, ApiResponseDefinition> responseDefinitions) {
        ApiResponses apiResponses = new ApiResponses();
        for (Map.Entry<String, ApiResponseDefinition> entry : responseDefinitions.entrySet()) {
            String code = entry.getKey();
            ApiResponseDefinition def = entry.getValue();
            apiResponses.addApiResponse(code, new ApiResponse()
                    .description(def.getDescription())
                    .content(new Content()
                            .addMediaType("application/json", new MediaType()
                                    .schema(def.getSchema())))
            );
        }
        return apiResponses;
    }
}
