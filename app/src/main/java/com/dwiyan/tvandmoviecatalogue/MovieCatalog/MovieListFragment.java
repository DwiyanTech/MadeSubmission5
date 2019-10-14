package com.dwiyan.tvandmoviecatalogue.MovieCatalog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.supportConfig.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {
    private View view;
    private TextView textView;

    private Context context;


    private int someStateValue;
    public final String StataeValue = "someValueToSave";
    public Parcelable parcelable;
    public int IdData;
    private AdapterMovie adapter;
    public ProgressBar progressBar;
    private  AppDatabase appDatabase;
    private List<MovieDataDB> movieDataDBList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_view, container, false);

        return rootView;
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {

        final MovieViewModel model = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        model.setSelectData();
        model.getMovieItem().observe(getActivity(), new Observer<List<MovieData>>() {
            @Override
            public void onChanged(@Nullable final List<MovieData> movieData) {

                progressBar.setVisibility(View.GONE);
                RecyclerView recyclerView = view.findViewById(R.id.search_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter = new AdapterMovie(movieData, R.layout.movie_card, getContext());
                recyclerView.setAdapter(adapter);

                ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        IdData = position + 1;
                        MovieData dataMovieset = new MovieData();
                        dataMovieset.setTitle(movieData.get(position).getTitle());
                        dataMovieset.setOverview(movieData.get(position).getOverview());
                        dataMovieset.setId(IdData);
                        dataMovieset.setReleaseDate(movieData.get(position).getReleaseDate());
                        dataMovieset.setPosterPath(movieData.get(position).getPosterPath());
                        dataMovieset.setPopularity(movieData.get(position).getPopularity());
                        dataMovieset.setBackdropPath(movieData.get(position).getBackdropPath());
                        Intent intent = new Intent(getActivity(), DetailMovie.class);
                        intent.putExtra(DetailMovie.EXTRA_PARCELABLE, dataMovieset);
                        startActivity(intent);

                    }
                });

            }
        });


    }


}



