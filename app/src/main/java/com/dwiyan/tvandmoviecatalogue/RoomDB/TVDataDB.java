package com.dwiyan.tvandmoviecatalogue.RoomDB;
import android.content.ContentValues;
import android.os.Build;
import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVData;

@Entity(tableName = "tvDataDB")
public class TVDataDB {

    @PrimaryKey(autoGenerate =  true)
    int id;
    @ColumnInfo(name = "vote_count")
    private int voteCount_Column;
    @ColumnInfo(name = "original_language")
    private String languageTV_Column;
    @ColumnInfo(name = "overview")
    private String overview_Column;
    @ColumnInfo(name = "name")
    private String nameTV_Column;
    @ColumnInfo(name = "popularity")
    private String rateTV_Column;
    @ColumnInfo(name = "first_air_date")
    private String firstDate_Column;
    @ColumnInfo(name = "poster_path")
    private String posterTV_Column;
    @ColumnInfo(name = "backdrop_path")
    private String backdropTV_Column;

    public static TVDataDB fromContentValues(ContentValues values) {
        final TVDataDB cheese = new TVDataDB();
        if (values.containsKey(BaseColumns._ID)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cheese.id = Math.toIntExact(values.getAsLong(BaseColumns._ID));
            }
        }
        if (values.containsKey(cheese.getNameTV_Column())) {
            cheese.nameTV_Column = values.getAsString(cheese.getNameTV_Column());
        }
        return cheese;
    }
    public int getVoteCount_Column() {
        return voteCount_Column;
    }

    public void setVoteCount_Column(int voteCount_Column) {
        this.voteCount_Column = voteCount_Column;
    }

    public String getLanguageTV_Column() {
        return languageTV_Column;
    }

    public void setLanguageTV_Column(String languageTV_Column) {
        this.languageTV_Column = languageTV_Column;
    }

    public String getOverview_Column() {
        return overview_Column;
    }

    public void setOverview_Column(String overview_Column) {
        this.overview_Column = overview_Column;
    }




    public String getNameTV_Column() {
        return nameTV_Column;
    }

    public void setNameTV_Column(String nameTV_Column) {
        this.nameTV_Column = nameTV_Column;
    }

    public String getRateTV_Column() {
        return rateTV_Column;
    }

    public void setRateTV_Column(String rateTV_Column) {
        this.rateTV_Column = rateTV_Column;
    }

    public String getFirstDate_Column() {
        return firstDate_Column;
    }

    public void setFirstDate_Column(String firstDate_Column) {
        this.firstDate_Column = firstDate_Column;
    }

    public String getPosterTV_Column() {
        return posterTV_Column;
    }

    public void setPosterTV_Column(String posterTV_Column) {
        this.posterTV_Column = posterTV_Column;
    }

    public String getBackdropTV_Column() {
        return backdropTV_Column;
    }

    public void setBackdropTV_Column(String backdropTV_Column) {
        this.backdropTV_Column = backdropTV_Column;
    }

}
