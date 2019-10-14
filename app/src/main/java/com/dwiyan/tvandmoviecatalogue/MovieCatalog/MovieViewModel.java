package com.dwiyan.tvandmoviecatalogue.MovieCatalog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dwiyan.tvandmoviecatalogue.ApiConfig.ApiConfig;
import com.dwiyan.tvandmoviecatalogue.BuildConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private final static String apikey = BuildConfig.TMDB_API_KEY;
   public MutableLiveData<List<MovieData>> movieDataItem = new MutableLiveData<>();


    public  void setSelectData(){
        MovieApiInterface movieApiInterface = ApiConfig.getApiWeb().create(MovieApiInterface.class);
        Call<MovieResponse> call = movieApiInterface.getMoviePopular(apikey);
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
