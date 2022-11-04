package com.example.moviecatalogservice.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogController {
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {

        RestTemplate restTemplate = new RestTemplate();

        // get all movie IDs
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 5),
            new Rating("5678", 4)
        );

        // For each movie ID call movie info service and get details
        // Put them all together
        return ratings.stream().map(rating -> { 
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class); 
            return new CatalogItem(movie.getName(), "Test", rating.getRating());
        })
        .collect(Collectors.toList());
    }
}
