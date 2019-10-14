package com.dwiyan.tvandmoviecatalogue.RoomDB;


import android.content.ContentValues;
import android.os.Build;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "movieDataDB")
public class MovieDataDB {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "poster_path")
    private String posterPath_column;
    @ColumnInfo(name = "overview")
    private String overview_column;
    @ColumnInfo(name = "release_date")
    private String releaseDate_column;
    @ColumnInfo(name = "title")
    private String title_column;
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath_column;
    @ColumnInfo(name = "popularity")
    private Double popularity_column;
    @ColumnInfo(name = "vote_count")
    private Integer voteCount_column;
    @ColumnInfo(name = "vote_average")
    private Double voteAverage_column;
    @ColumnInfo(name = "favourite")
    private Boolean favourite_column;

    public static MovieDataDB fromContentValues(ContentValues values) {
        final MovieDataDB cheese = new MovieDataDB();
        if (values.containsKey(BaseColumns._ID)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cheese.id = Math.toIntExact(values.getAsLong(BaseColumns._ID));
            }
        }
        if (values.containsKey(cheese.getTitle_column())) {
            cheese.title_column = values.getAsString(cheese.getTitle_column());
        }
        return cheese;
    }
    public Boolean getFavourite_column() {
        return favourite_column;
    }

    public void setFavourite_column(Boolean favourite_column) {
        this.favourite_column = favourite_column;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath_column() {
        return posterPath_column;
    }

    public void setPosterPath_column(String posterPath_column) {
        this.posterPath_column = posterPath_column;
    }

    public String getOverview_column() {
        return overview_column;
    }

    public void setOverview_column(String overview_column) {
        this.overview_column = overview_column;
    }

    public String getReleaseDate_column() {
        return releaseDate_column;
    }

    public void setReleaseDate_column(String releaseDate_column) {
        this.releaseDate_column = releaseDate_column;
    }

    public String getTitle_column() {
        return title_column;
    }

    public void setTitle_column(String title_column) {
        this.title_column = title_column;
    }

    public String getBackdropPath_column() {
        return backdropPath_column;
    }

    public void setBackdropPath_column(String backdropPath_column) {
        this.backdropPath_column = backdropPath_column;
    }

    public Double getPopularity_column() {
        return popularity_column;
    }

    public void setPopularity_column(Double popularity_column) {
        this.popularity_column = popularity_column;
    }

    public Integer getVoteCount_column() {
        return voteCount_column;
    }

    public void setVoteCount_column(Integer voteCount_column) {
        this.voteCount_column = voteCount_column;
    }

    public Double getVoteAverage_column() {
        return voteAverage_column;
    }

    public void setVoteAverage_column(Double voteAverage_column) {
        this.voteAverage_column = voteAverage_column;
    }
}
