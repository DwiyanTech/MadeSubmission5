package com.dwiyan.tvandmoviecatalogue.FavouriteFragment.MovieFavourite;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDB;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVData;

import java.util.ArrayList;
import java.util.List;

public class MovieFavourite extends Fragment {

    private MovieFavouriteAdapter adapterMovFav;
    private AppDatabase appDatabase;
    private List<MovieDataDB> movieDataDBList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_view,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.search_recycler);

        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());
        adapterMovFav = new MovieFavouriteAdapter(movieDataDBList,R.layout.movie_card,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterMovFav);


    }
}
