package com.dwiyan.tvandmoviecatalogue.SearchTV;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwiyan.tvandmoviecatalogue.MovieCatalog.DetailMovie;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.SearchMovie.ActivityMovieSearch;
import com.dwiyan.tvandmoviecatalogue.SearchMovie.AdapterSearch;
import com.dwiyan.tvandmoviecatalogue.SearchMovie.SearchMovieViewModel;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVData;
import com.dwiyan.tvandmoviecatalogue.supportConfig.ItemClickSupport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SearchTVActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private AdapterSearchTV adapterSearchTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        final TVSearchViewModel tvSearchViewModel = ViewModelProviders.of(this).get(TVSearchViewModel.class);

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        if (searchManager != null) {
            final SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_tv));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    tvSearchViewModel.setTvDataLive(SearchTVActivity.this, query);
                    progressBar.setVisibility(View.VISIBLE);
                    final RecyclerView recyclerView = findViewById(R.id.search_recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchTVActivity.this));
                    tvSearchViewModel.getTvData().observe(SearchTVActivity.this, new Observer<List<TVData>>() {
                        @Override
                        public void onChanged(final List<TVData> tvData) {
                            progressBar.setVisibility(View.GONE);
                            adapterSearchTV = new AdapterSearchTV(SearchTVActivity.this, R.layout.movie_card, tvData);
                            recyclerView.setAdapter(adapterSearchTV);
                            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                                @Override
                                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                                    int IdData = position + 1;
                                    TVData dataTVset = new TVData();
                                    dataTVset.setNameTV(tvData.get(position).getNameTV());
                                    dataTVset.setOverview(tvData.get(position).getOverview());
                                    dataTVset.setId(IdData);
                                    dataTVset.setFirstDate(tvData.get(position).getRateTV());
                                    dataTVset.setPosterTV(tvData.get(position).getPosterTV());
                                    dataTVset.setRateTV(tvData.get(position).getRateTV());
                                    dataTVset.setBackdropTV(tvData.get(position).getBackdropTV());
                                    Intent intent = new Intent(SearchTVActivity.this, DetailMovie.class);
                                    intent.putExtra(DetailMovie.EXTRA_PARCELABLE, dataTVset);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }
        return true;
    }
}


