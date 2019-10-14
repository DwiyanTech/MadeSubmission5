package com.example.favouritetvmovie.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favouritetvmovie.FavMovie.MovieData;
import com.example.favouritetvmovie.R;

import java.util.List;

public class AdapterFavMovie extends RecyclerView.Adapter<AdapterFavMovie.AdapterFavMovieViewHolder> {

    private List<MovieData> movies;

    private int rowLayout;
    private Context context;
    public AdapterFavMovie( int rowLayout, Context context,List<MovieData> movies) {

        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;

    }


    @NonNull
    @Override
    public AdapterFavMovie.AdapterFavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterFavMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavMovie.AdapterFavMovieViewHolder holder, int position) {


        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.data.setText(movies.get(position).getReleaseDate());


        Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + movies.get(position).getPosterPath()).into(holder.backbg);
    }

    @Override
    public int getItemCount() {
    return movies.size();
    }


    public class AdapterFavMovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        ImageView backbg;
        Button btnShare;
        Button btnFavorite;

        public AdapterFavMovieViewHolder(@NonNull View v) {
            super(v);

            movieTitle = (TextView) v.findViewById(R.id.tv_item_title);
            data = (TextView) v.findViewById(R.id.tv_item_date);
            movieDescription = (TextView) v.findViewById(R.id.tv_item_overview);
            btnFavorite = (Button) v.findViewById(R.id.btn_set_favorite);
            btnShare = (Button) v.findViewById(R.id.btn_set_share);
            backbg = (ImageView) v.findViewById(R.id.img_item_photo);
        }
    }
}
