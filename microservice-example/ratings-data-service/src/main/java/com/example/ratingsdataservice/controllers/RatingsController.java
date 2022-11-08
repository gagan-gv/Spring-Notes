package com.example.ratingsdataservice.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratings")
public class RatingsController {
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable String userId) {
        List<Rating> ratings = Arrays.asList(
            new Rating("1234", 5),
            new Rating("5678", 4)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
