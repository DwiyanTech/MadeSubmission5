package com.dwiyan.tvandmoviecatalogue.FavouriteFragment.MovieFavourite;

import android.content.Context;
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
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;

import java.util.ArrayList;
import java.util.List;

public class MovieFavouriteAdapter extends RecyclerView.Adapter<MovieFavouriteAdapter.MovieFavouriteViewHolder> {

    private List<MovieDataDB> movieDataDBList;
    private int rowLayout;
    private Context context;
    private AppDatabase appDatabase;
    private Boolean favouriteClicked;

    public MovieFavouriteAdapter(List<MovieDataDB> movieDataDBList, int rowLayout, Context context) {
        this.movieDataDBList = movieDataDBList;
        this.rowLayout = rowLayout;
        this.context = context;


    }

    @NonNull
    @Override
    public MovieFavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieFavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieFavouriteViewHolder holder, final int position) {
    holder.movieTitle.setText(movieDataDBList.get(position).getTitle_column());
    holder.data.setText(movieDataDBList.get(position).getReleaseDate_column());

    holder.movieDescription.setText(movieDataDBList.get(position).getOverview_column());
        holder.btnFavorite.setText(context.getString(R.string.unfavourite));holder.btnShare.setVisibility(View.GONE);
    holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        DeleteItem(position);
        }
    });
    Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + movieDataDBList.get(position).getPosterPath_column()).into(holder.backbg);
    }

    @Override
    public int getItemCount() {
        return movieDataDBList.size();
    }

    public class MovieFavouriteViewHolder extends RecyclerView.ViewHolder {

        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView backbg;
        Button btnShare;
        Button btnFavorite;


        public MovieFavouriteViewHolder(@NonNull View v) {
            super(v);

            movieTitle = (TextView) v.findViewById(R.id.tv_item_title);
            data = (TextView) v.findViewById(R.id.tv_item_date);
            movieDescription = (TextView) v.findViewById(R.id.tv_item_overview);
            btnFavorite = (Button) v.findViewById(R.id.btn_set_favorite);
            btnShare = (Button) v.findViewById(R.id.btn_set_share);
            backbg = (ImageView) v.findViewById(R.id.img_item_photo);
        }
    }

    public void DeleteItem(int position){
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        appDatabase.movieDataDBDao().DeleteItem(movieDataDBList.get(position));
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());
        notifyDataSetChanged();
        Toast.makeText(context,"Delete Data Berhasil ",Toast.LENGTH_LONG).show();
    }
}
