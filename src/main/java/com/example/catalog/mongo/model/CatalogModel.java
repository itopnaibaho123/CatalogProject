package com.example.catalog.mongo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "catalog")
public class CatalogModel  implements Serializable {
    @Id
    private String id;

    @NotNull
    private String nama;

    @NotNull
    private int jumlah;

    @NotNull
    private int harga;

    @NotNull
    private String description;


}
