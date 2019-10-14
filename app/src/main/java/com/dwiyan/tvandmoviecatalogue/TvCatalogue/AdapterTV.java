package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.AdapterMovie;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.R;

import java.util.List;

public class AdapterTV extends RecyclerView.Adapter<AdapterTV.TvViewHolder> {

    private List<TVData> tvData;
    private int rowLayout;
    private Context context;
    public class TvViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvdate;
        TextView TVDescription;
        TextView rating;
        ImageView backbg;
        Button btnShare;
        Button btnFavorite;
        public TvViewHolder( View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_item_title);
            tvdate = (TextView) v.findViewById(R.id.tv_item_date);
            TVDescription = (TextView) v.findViewById(R.id.tv_item_overview);
            btnFavorite = (Button) v.findViewById(R.id.btn_set_favorite);
            btnShare = (Button) v.findViewById(R.id.btn_set_share);
            backbg = (ImageView) v.findViewById(R.id.img_item_photo);
        }
    }

    public AdapterTV(List<TVData> tvData, int rowLayout , Context context){
        this.tvData = tvData;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterTV.TvViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TvViewHolder(view);


    }

    @Override
    public void onBindViewHolder(TvViewHolder tvViewHolder, int position) {
                  tvViewHolder.tvTitle.setText(tvData.get(position).getNameTV());
                  tvViewHolder.tvdate.setText(tvData.get(position).getFirstDate());
                  tvViewHolder.TVDescription.setText(tvData.get(position).getOverview());
        Glide.with(context).load("https://image.tmdb.org/t/p/w342/" + tvData.get(position).getPosterTV()).into(tvViewHolder.backbg);
    }

    @Override
    public int getItemCount() {
        if(tvData == null){
            return 0;
        } else {
            return tvData.size();
        }

    }
}
