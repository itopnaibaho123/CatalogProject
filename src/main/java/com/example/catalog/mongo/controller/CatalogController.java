package com.example.catalog.mongo.controller;

import com.example.catalog.mongo.model.CatalogModel;
import com.example.catalog.mongo.request.CatalogRequest;
import com.example.catalog.mongo.response.ResponseHandler;
import com.example.catalog.mongo.service.CatalogServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
@Tag(name="Catalog", description = "Catalog CRUD")
public class CatalogController {
    @Autowired
    CatalogServiceImpl catalogService;

    @GetMapping("/{Id}")
    public ResponseEntity<Object> getCatalogById(@Parameter(
            description = "ID Catalog that will be Query",
            required = true,
            example = "123456")@PathVariable("Id") String Id)
    {
        return ResponseHandler.responseBuilder("Requested Catalog Details are given here",
                HttpStatus.OK, catalogService.getCatalog(Id));
    }
    @GetMapping("/")
    public ResponseEntity<Object> getAllCatalog()
    {
        return ResponseHandler.responseBuilder("Success Getting all Catalogs", HttpStatus.OK, catalogService.listCatalog());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCatalog(@RequestBody CatalogRequest catalogRequest)
    {
        return ResponseHandler.responseBuilder("Requested Catalog Created",
                HttpStatus.CREATED, catalogService.createCatalog(catalogRequest));

    }

    @PutMapping("/")
    public ResponseEntity<Object> updateCatalog(@RequestBody CatalogModel catalogModel)
    {

        return ResponseHandler.responseBuilder("Requested Catalog has been updated",
            HttpStatus.OK, catalogService.updateCatalog(catalogModel));
    }

    @DeleteMapping("/{Id}")
    public String deleteCatalog(@Parameter(
            description = "ID Catalog that will be delete ",
            required = true,
            example = "123456")@PathVariable("Id") String id)
    {
        catalogService.deleteCatalog(id);
        return "Cloud Vendor Deleted Successfully";
    }
}
