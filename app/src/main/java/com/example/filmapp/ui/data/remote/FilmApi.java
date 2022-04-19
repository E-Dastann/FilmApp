package com.example.filmapp.ui.data.remote;

import com.example.filmapp.ui.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FilmApi {
    @GET("/films")
    Call<List<Film>>getFilms();

    @GET("/films/{id}")
    Call<Film> getDetailFilms(
       @Path("id")
       String id
    );
}
