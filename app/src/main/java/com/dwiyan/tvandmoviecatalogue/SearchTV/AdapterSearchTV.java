package com.dwiyan.tvandmoviecatalogue.SearchTV;

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

import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.SearchMovie.AdapterSearch;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVData;
import com.dwiyan.tvandmoviecatalogue.supportConfig.CustomClick;

import java.util.List;

public class AdapterSearchTV extends RecyclerView.Adapter<AdapterSearchTV.TVSearchViewHolder> {


    private AppDatabase appDatabase;
    private List<TVData> tvData;

    private int rowLayout;
    private Context context;
    public boolean[] favourite;
    @NonNull
    @Override
    public AdapterSearchTV.TVSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AdapterSearchTV.TVSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearchTV.TVSearchViewHolder holder, int position) {
        holder.movieTitle.setText(tvData.get(position).getNameTV());
        holder.data.setText(tvData.get(position).getFirstDate());
        holder.movieDescription.setText(tvData.get(position).getOverview());
        holder.btnShare.setOnClickListener(new CustomClick(position, new CustomClick.ItemClickCallback() {
            @Override
            public void ItemClicked(View v, int position) {
                Toast.makeText(context, "Share " + tvData.get(position).getNameTV(), Toast.LENGTH_LONG).show();
            }
        }));

        holder.btnFavorite.setOnClickListener(new CustomClick(position, new CustomClick.ItemClickCallback() {
            @Override
            public void ItemClicked(View v, int position) {





            }
        }));
    }

    public AdapterSearchTV(Context context, int rowLayout , List<TVData> tvData){
        this.context = context;
        this.rowLayout = rowLayout;
        this.tvData = tvData;


    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class TVSearchViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        ImageView backbg;
        Button btnShare;
        Button btnFavorite;
        public TVSearchViewHolder(@NonNull View v) {
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
