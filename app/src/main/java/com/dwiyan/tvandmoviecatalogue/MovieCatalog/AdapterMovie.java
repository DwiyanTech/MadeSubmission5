package com.dwiyan.tvandmoviecatalogue.MovieCatalog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.supportConfig.CustomClick;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MovieViewHolder> {

    private AppDatabase appDatabase;
    private List<MovieData> movies;
    private List<MovieDataDB> movieDataDBList;
    private int rowLayout;
    private Context context;
   public boolean[] favourite;

    public void setData(List<MovieData> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        ImageView backbg;
        Button btnShare;
        Button btnFavorite;


        public MovieViewHolder(View v) {
            super(v);

            movieTitle = (TextView) v.findViewById(R.id.tv_item_title);
            data = (TextView) v.findViewById(R.id.tv_item_date);
            movieDescription = (TextView) v.findViewById(R.id.tv_item_overview);
            btnFavorite = (Button) v.findViewById(R.id.btn_set_favorite);
            btnShare = (Button) v.findViewById(R.id.btn_set_share);
            backbg = (ImageView) v.findViewById(R.id.img_item_photo);

        }
    }

    public AdapterMovie(List<MovieData> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());
    }

    @Override
    public AdapterMovie.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    public String setTanggal(String data)
    {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        String date_of_release = null;
        try {
            Date date = date_format.parse(data);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            date_of_release = new_date_format.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date_of_release;
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.btnShare.setOnClickListener(new CustomClick(position, new CustomClick.ItemClickCallback() {
            @Override
            public void ItemClicked(View v, int position) {
                Toast.makeText(context, "Share " + movies.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        }));

        holder.btnFavorite.setOnClickListener(new CustomClick(position, new CustomClick.ItemClickCallback() {
            @Override
            public void ItemClicked(View v, int position) {





            }
        }));



        Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + movies.get(position).getPosterPath()).into(holder.backbg);
    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {
            return movies.size();
        }
    }

}

