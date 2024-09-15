package com.example.catalog.mongo.service;


import com.example.catalog.mongo.exception.CatalogNotFoundException;
import com.example.catalog.mongo.model.CatalogModel;
import com.example.catalog.mongo.repository.CatalogRepository;
import com.example.catalog.mongo.request.CatalogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "product")
public class CatalogServiceImpl implements CatalogService  {

    @Autowired
    CatalogRepository catalogRepository;


    public CatalogModel createCatalog(CatalogRequest catalogRequest) {
        System.out.println(catalogRequest.getDescription());
        CatalogModel catalogModel = CatalogModel.builder()
                        .nama(catalogRequest.getNama())
                        .harga(catalogRequest.getHarga())
                        .description(catalogRequest.getDescription())
                        .jumlah(catalogRequest.getJumlah()).build();

        return  catalogRepository.save(catalogModel);
    }

    @CachePut(key = "#catalogModel.id")
    public CatalogModel updateCatalog(CatalogModel catalogModel) {
        System.out.println(catalogModel);
        return catalogRepository.save(catalogModel);

    }

    @CacheEvict(key = "#id", beforeInvocation = true)
    public String deleteCatalog(String id) {

        catalogRepository.deleteById(id);
        return "Success";
    }


    @Cacheable(key = "#id")
    public CatalogModel getCatalog(String id) {
        if(catalogRepository.findById(id).isEmpty()){
            throw new CatalogNotFoundException("Requested Catalog does not exist");

        }
        return catalogRepository.findById(id).get();
    }


    public List<CatalogModel> listCatalog() {
        return catalogRepository.findAll();
    }
}

