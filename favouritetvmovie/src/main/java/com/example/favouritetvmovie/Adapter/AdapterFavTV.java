package com.example.favouritetvmovie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.favouritetvmovie.FavMovie.MovieData;
import com.example.favouritetvmovie.FavTV.TVData;
import com.example.favouritetvmovie.R;

import java.util.List;

public class AdapterFavTV extends RecyclerView.Adapter<AdapterFavTV.AdapterTVVieHolder> {

    private List<TVData> tvData;

    private int rowLayout;
    private Context context;

    public AdapterFavTV(List<TVData> tvData, int rowLayout, Context context) {
        this.tvData = tvData;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFavTV.AdapterTVVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterFavTV.AdapterTVVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavTV.AdapterTVVieHolder holder, int position) {

        holder.movieTitle.setText(tvData.get(position).getNameTV());
        holder.movieDescription.setText(tvData.get(position).getOverview());
        holder.data.setText(tvData.get(position).getFirstDate());

        Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + tvData.get(position).getPosterTV()).into(holder.backbg);


    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    public class AdapterTVVieHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        ImageView backbg;
        Button btnShare;
        Button btnFavorite;
        public AdapterTVVieHolder(@NonNull View v) {
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
