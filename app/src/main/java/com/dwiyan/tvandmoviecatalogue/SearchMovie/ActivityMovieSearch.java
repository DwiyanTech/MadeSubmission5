package com.dwiyan.tvandmoviecatalogue.SearchMovie;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwiyan.tvandmoviecatalogue.BuildConfig;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.DetailMovie;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.supportConfig.ItemClickSupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ActivityMovieSearch extends AppCompatActivity {
    private final static String apikey = BuildConfig.TMDB_API_KEY;
    private AdapterSearch adapterSearch;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.search_movie);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final SearchMovieViewModel searchMovieViewModel = ViewModelProviders.of(this).get(SearchMovieViewModel.class);

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        if (searchManager != null) {
            final SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_movie));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(ActivityMovieSearch.this, query, Toast.LENGTH_SHORT).show();
                    searchMovieViewModel.setSelectData(query);
                    progressBar.setVisibility(View.VISIBLE);
                    final RecyclerView recyclerView = findViewById(R.id.search_recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ActivityMovieSearch.this));

                    searchMovieViewModel.getMovieItem().observe(ActivityMovieSearch.this, new Observer<List<MovieData>>() {
                        @Override
                        public void onChanged(@Nullable final List<MovieData> movieData) {
                            progressBar.setVisibility(View.GONE);
                            adapterSearch = new AdapterSearch(movieData, R.layout.movie_card, ActivityMovieSearch.this);
                            recyclerView.setAdapter(adapterSearch);
                            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                @Override
                                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                    int IdData = position + 1;
                                    MovieData dataMovieset = new MovieData();
                                    dataMovieset.setTitle(movieData.get(position).getTitle());
                                    dataMovieset.setOverview(movieData.get(position).getOverview());
                                    dataMovieset.setId(IdData);
                                    dataMovieset.setReleaseDate(movieData.get(position).getReleaseDate());
                                    dataMovieset.setPosterPath(movieData.get(position).getPosterPath());
                                    dataMovieset.setPopularity(movieData.get(position).getPopularity());
                                    dataMovieset.setBackdropPath(movieData.get(position).getBackdropPath());
                                    Intent intent = new Intent(ActivityMovieSearch.this, DetailMovie.class);
                                    intent.putExtra(DetailMovie.EXTRA_PARCELABLE, dataMovieset);
                                    startActivity(intent);

                                }
                            });

                        }
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }


}
