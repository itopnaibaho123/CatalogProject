package com.example.catalog.mongo.service;



import com.example.catalog.mongo.model.CatalogModel;
import com.example.catalog.mongo.request.CatalogRequest;

import java.util.List;

public interface CatalogService {
    public CatalogModel createCatalog(CatalogRequest catalogRequest);
    public CatalogModel updateCatalog(CatalogModel catalogModel);
    public String deleteCatalog(String id);
    public CatalogModel getCatalog(String id);
    public List<CatalogModel> listCatalog();
}
