package com.example.filmapp.ui;

import android.app.Application;

import com.example.filmapp.ui.data.remote.FilmApi;
import com.example.filmapp.ui.data.remote.RetrofitClient;

import retrofit2.Retrofit;

public class App extends Application {
    private RetrofitClient retrofitClient;
    public static FilmApi api;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient =  new RetrofitClient();
        api=retrofitClient.createFilmApi();
    }
}
