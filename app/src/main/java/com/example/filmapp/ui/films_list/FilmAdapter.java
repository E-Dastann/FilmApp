package com.example.filmapp.ui.films_list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmapp.databinding.ItemFilmBinding;
import com.example.filmapp.ui.data.models.Film;
import com.example.filmapp.ui.data.remote.OnClickData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private List<Film> film = new ArrayList<>();
    private OnClickData listener;

    public void setFilm(List<Film> film) {
        this.film = film;
        notifyDataSetChanged();
    }

    public FilmAdapter(OnClickData listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmViewHolder(ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(film.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.print(film.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return film.size();
    }



    protected class FilmViewHolder extends RecyclerView.ViewHolder {
        private ItemFilmBinding binding;

        public FilmViewHolder(ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());
            Glide.with(binding.image).load(film.getMovieBanner()).into(binding.image);

        }
    }

}
