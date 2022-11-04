package com.example.moviecatalogservice.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogController {
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        return Collections.singletonList(
            new CatalogItem("Transformers", "Test", 4)
        );
    }
}
