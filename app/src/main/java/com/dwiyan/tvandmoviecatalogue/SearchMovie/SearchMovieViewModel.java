package com.dwiyan.tvandmoviecatalogue.SearchMovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dwiyan.tvandmoviecatalogue.ApiConfig.ApiConfig;
import com.dwiyan.tvandmoviecatalogue.BuildConfig;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieApiInterface;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchMovieViewModel extends ViewModel {
    private final static String apikey = BuildConfig.TMDB_API_KEY;
    public MutableLiveData<List<MovieData>> movieDataItem = new MutableLiveData<>();


    public  void setSelectData(String query){
        MovieApiInterface movieApiInterface = ApiConfig.getApiWeb().create(MovieApiInterface.class);
        Call<MovieResponse> call = movieApiInterface.getSearchMovie(query,apikey);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<MovieData> movieData = response.body().getResults();

                movieDataItem.setValue(movieData);

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {



            }
        });

    }
    LiveData<List<MovieData>> getMovieItem() {
        return movieDataItem;
    }

}
