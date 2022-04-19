package com.example.filmapp.ui.film_detaiel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.filmapp.databinding.FragmentDetailBinding;
import com.example.filmapp.ui.App;
import com.example.filmapp.ui.data.models.Film;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {
    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = requireArguments().getString("id");

        App.api.getDetailFilms(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                Film film = response.body();
                Glide.with(requireContext()).load(film.getImage()).into(binding.imageDetils);
                binding.tvDescription.setText(film.getDescription());
                binding.tvTitle.setText(film.getTitle());
                binding.tvProducer.setText(film.getDirector());

            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }
}