package com.dwiyan.tvandmoviecatalogue.FavouriteFragment.TVFavourite;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDB;
import java.util.ArrayList;
import java.util.List;

public class TVFavaourite extends Fragment {
AppDatabase appDatabase;
List<TVDataDB> tvDataDBList;
tvFavouriteAdapter adapterTVFav;
ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_view,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.search_recycler);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class,"tvDataDB").allowMainThreadQueries().build();
        tvDataDBList = new ArrayList<>();
        tvDataDBList.addAll(appDatabase.tvDataDBDao().tvDataDBList());
        adapterTVFav = new tvFavouriteAdapter(tvDataDBList,R.layout.movie_card,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterTVFav);
        
    }


}
