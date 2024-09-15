package com.example.catalog.mongo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogRequest implements Serializable {
    String nama;
    int jumlah;
    int harga;
    String description;
}
