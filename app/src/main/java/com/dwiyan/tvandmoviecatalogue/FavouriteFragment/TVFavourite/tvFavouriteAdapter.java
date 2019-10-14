package com.dwiyan.tvandmoviecatalogue.FavouriteFragment.TVFavourite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDB;

import java.util.ArrayList;
import java.util.List;

public class tvFavouriteAdapter extends RecyclerView.Adapter<tvFavouriteAdapter.AdapterViewHolder> {
    @NonNull

    private List<TVDataDB> tvDataDBList;
    private int rowLayout;
    private Context context;
    private AppDatabase appDatabase;
    private Boolean favouriteClicked;
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);

        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, final int position) {
        holder.tvTitle.setText(tvDataDBList.get(position).getNameTV_Column());
        holder.TVDescription.setText(tvDataDBList.get(position).getFirstDate_Column());
        holder.btnFavorite.setText(context.getString(R.string.unfavourite));holder.btnShare.setVisibility(View.GONE);
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItem(position);

            }
        });
        Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + tvDataDBList.get(position).getPosterTV_Column()).into(holder.backbg);
    }

    public tvFavouriteAdapter(List<TVDataDB> tvDataDBList, int rowLayout, Context context) {
        this.tvDataDBList = tvDataDBList;
        this.rowLayout = rowLayout;
        this.context = context;


    }
    @Override
    public int getItemCount() {
        return tvDataDBList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvdate;
        TextView TVDescription;
        TextView rating;
        ImageView backbg;
        Button btnShare;
        Button btnFavorite;
        public AdapterViewHolder(@NonNull View v) {

            super(v);

            tvTitle = (TextView) v.findViewById(R.id.tv_item_title);
           tvdate = (TextView) v.findViewById(R.id.tv_item_date);
            TVDescription = (TextView) v.findViewById(R.id.tv_item_overview);
            btnFavorite = (Button) v.findViewById(R.id.btn_set_favorite);
            btnShare = (Button) v.findViewById(R.id.btn_set_share);
            backbg = (ImageView) v.findViewById(R.id.img_item_photo);
        }
    }
    public void DeleteItem(int position){
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"tvDataDB").allowMainThreadQueries().build();
        appDatabase.tvDataDBDao().DeleteItem(tvDataDBList.get(position));
        tvDataDBList = new ArrayList<>();
       tvDataDBList.addAll(appDatabase.tvDataDBDao().tvDataDBList());
        notifyDataSetChanged();
        Toast.makeText(context,"Delete Data Berhasil ",Toast.LENGTH_LONG).show();
    }


}
