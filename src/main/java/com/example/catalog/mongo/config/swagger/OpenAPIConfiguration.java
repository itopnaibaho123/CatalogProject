package com.example.catalog.mongo.config.swagger;

import com.example.catalog.mongo.exception.CatalogBadRequestException;
import com.example.catalog.mongo.exception.CatalogException;
import com.example.catalog.mongo.exception.CatalogNotFoundException;
import com.example.catalog.mongo.model.CatalogModel;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Christopher Moses Nathanael");
        myContact.setEmail("christopher.naibaho14@gmail.com");

        Info information = new Info()
                .title("Catalog Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage Catalog.")
                .contact(myContact)
                ;
        return new OpenAPI().info(information).servers(List.of(server))
                .components(new Components()
                        .addSchemas("CatalogNotFoundException", new Schema<CatalogNotFoundException>()

                                .addProperty("message",new Schema<>().type("string"))
                                .addProperty("httpStatus", new Schema<>().type("string")))
                        .addSchemas("CatalogBadRequestException", new Schema<CatalogBadRequestException>()
                                .addProperty("message",new Schema<>().type("string"))
                                .addProperty("httpStatus", new Schema<>().type("string")))

                )



                .path("/catalog/", catalogPathItem())
                .path("/catalog/{Id}", catalogByIdPathItem());
    }
    private PathItem catalogPathItem() {
        return new PathItem()
                .post(OperationUtils.createOperation("Create Catalog",
                        "Create a new catalog",
                        null,
                        Map.of(
                                "201",  new ApiResponseDefinition(
                                        "Catalog Successfuly Created",
                                        CatalogModel.class
                                )
                                ,
                                "400", new ApiResponseDefinition(
                                        "Invalid Catalog Data",
                                        CatalogNotFoundException.class
                                ),
                                "401", new ApiResponseDefinition(
                                        "Catalog Bad Request",
                                        CatalogBadRequestException.class
                                )
                        ),
                        catalogRequestBodyCatalog(),
                        false,
                        true,
                        false,
                        false))
                .get(OperationUtils.createOperation("Get List Catalog",
                        "Retrieve a list of all catalogs",
                        null,
                        Map.of(
                                "200", new ApiResponseDefinition(
                                        "Get All Catalog Successfully",
                                        CatalogModel.class
                                ),
                                "404", new ApiResponseDefinition(
                                        "Catalog Not Found",
                                        CatalogNotFoundException.class
                                )
                        ),
                        null,
                        true,
                        false,
                        false,
                        false))
                .put(OperationUtils.createOperation("Update Catalog",
                        "Update an existing catalog",
                        idParameter(),
                        Map.of(
                                "200", new ApiResponseDefinition(
                                        "Catalog Updated Successfully",
                                        CatalogModel.class
                                ),
                                "404", new ApiResponseDefinition(
                                        "Catalog Not Found",
                                        CatalogNotFoundException.class
                                ),"401", new ApiResponseDefinition(
                                        "Catalog Bad Request",
                                        CatalogBadRequestException.class
                                )
                        ),
                        catalogRequestBodyCatalog(),
                        false,
                        false,
                        true,
                        false));
    }

    private PathItem catalogByIdPathItem() {
        return new PathItem()
                .get(OperationUtils.createOperation("Get Catalog by ID",
                        "Retrieve a catalog by its ID",
                        idParameter(),
                        Map.of(
                                "200", new ApiResponseDefinition(
                                        "Success Getting Catalog by ID",
                                        CatalogModel.class
                                ),
                                "404", new ApiResponseDefinition(
                                        "Catalog Not Found",
                                        CatalogNotFoundException.class
                                )
                        ),
                        null,
                        true,
                        false,
                        false,
                        false))

                .delete(OperationUtils.createOperation("Delete Catalog",
                        "Delete an existing catalog",
                        idParameter(),
                        Map.of(
                                "200", new ApiResponseDefinition(
                                        "Catalog Deleted Successfully",
                                        CatalogModel.class
                                ),
                                "404", new ApiResponseDefinition(
                                        "Catalog Not Found",
                                        CatalogNotFoundException.class
                                ),
                                "401", new ApiResponseDefinition(
                                        "Catalog Bad Request",
                                        CatalogBadRequestException.class
                                )
                        ),
                        null,
                        false,
                        false,
                        false,
                        true));
    }

    private List<Parameter> idParameter() {
        return List.of(new Parameter()
                .name("id")
                .description("ID of the catalog")
                .required(true)
                .in("path")
                .schema(new Schema<String>().type("string")));
    }

    private RequestBody catalogRequestBodyCatalog() {
        return new RequestBody()

                .required(true)
                .description("Catalog data")
                .content(new Content()
                        .addMediaType("application/json", new MediaType()
                                .schema(new Schema<>()
                                        .$ref("#/components/schemas/Catalog"))));
    }

}
