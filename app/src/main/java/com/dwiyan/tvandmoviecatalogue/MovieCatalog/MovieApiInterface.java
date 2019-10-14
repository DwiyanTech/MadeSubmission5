package com.dwiyan.tvandmoviecatalogue.MovieCatalog;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieApiInterface {

    @GET("movie/popular")
    Call<MovieResponse> getMoviePopular(@Query("api_key") String ApiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/movie/")
    Call<MovieResponse> getSearchMovie(@Query("query")String search, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieResponse> getMovieByDateRelease(@Query("api_key") String apiKey,@Query("primary_release_date.gte") String DateGTENow,@Query("primary_release_date.lte") String DateLTENow);

}
