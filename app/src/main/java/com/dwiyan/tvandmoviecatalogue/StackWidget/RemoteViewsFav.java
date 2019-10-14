package com.dwiyan.tvandmoviecatalogue.StackWidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.bumptech.glide.Glide.with;

public class RemoteViewsFav implements RemoteViewsService.RemoteViewsFactory {

    private List<MovieDataDB> movieDataDBList;

    private List<Bitmap> moviePoster;
    private  Context context;
    private AppDatabase appDatabase;


    public RemoteViewsFav(Context context){
        this.context = context;
    }
    @Override
    public void onCreate() {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());



    }



    @Override
    public void onDataSetChanged() {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movieDataDBList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);


        try {
            rv.setImageViewBitmap(position, Glide.with(context).asBitmap().load("https://image.tmdb.org/t/p/w342/" + movieDataDBList.get(position).getPosterPath_column()).submit().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Bundle extras = new Bundle();
        extras.putInt(FavouriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
