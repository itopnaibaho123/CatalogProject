package com.example.catalog.mongo.repository;

import com.example.catalog.mongo.model.CatalogModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogRepository extends MongoRepository<CatalogModel, String> {
    List<CatalogModel> findByNama(String nama);
}
