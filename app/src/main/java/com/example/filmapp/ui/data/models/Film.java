package com.example.filmapp.ui.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Film implements Serializable {
    String id;
    String title;
    @SerializedName("original_title")
    String originalTitle;
    String description;
    @SerializedName("movie_banner")
    String movieBanner;
    String director;

    public String getDirector() {
        return director;
    }

    public String getMovieBanner() {
        return movieBanner;
    }

    String image;

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }
}
