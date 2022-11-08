package com.example.moviecatalogservice.models;

import java.util.List;

public class UserRating {
    private List<Rating> UserRating;

    public UserRating() {}

    public List<Rating> getUserRating() {
        return UserRating;
    }

    public void setUserRating(List<Rating> userRating) {
        UserRating = userRating;
    }
    
}
