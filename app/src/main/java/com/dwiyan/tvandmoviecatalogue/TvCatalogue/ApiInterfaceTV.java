package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceTV {

    @GET("tv/popular")
    Call<TVResponse> getTVPopular(@Query("api_key") String apiKey);
    @GET("search/tv/")
    Call<TVResponse> getSearchTV(@Query("query")String search, @Query("api_key") String apiKey);

}
