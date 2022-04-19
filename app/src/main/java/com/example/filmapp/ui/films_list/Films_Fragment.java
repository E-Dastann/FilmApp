package com.example.filmapp.ui.films_list;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.filmapp.R;
import com.example.filmapp.databinding.FragmentFilmsBinding;
import com.example.filmapp.ui.App;
import com.example.filmapp.ui.data.models.Film;
import com.example.filmapp.ui.data.remote.OnClickData;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Films_Fragment extends Fragment implements OnClickData {
    private FragmentFilmsBinding binding;
    private FilmAdapter adapter;


    public Films_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmAdapter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setFilm(response.body());

                } else {
                    Snackbar.make(
                            binding.getRoot(),
                            response.message(),
                            BaseTransientBottomBar.LENGTH_LONG
                    ).setBackgroundTint(Color.RED).show();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Snackbar.make(
                        binding.getRoot(),
                        t.getLocalizedMessage(),
                        BaseTransientBottomBar.LENGTH_LONG
                ).setBackgroundTint(Color.RED).show();
            }
        });
    }

    @Override
    public void print(Film film) {
        Bundle bundle = new Bundle();
        bundle.putString("id", film.getId());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.detailFragment, bundle);
    }
}