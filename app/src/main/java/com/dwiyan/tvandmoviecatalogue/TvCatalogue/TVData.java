package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TVData implements Parcelable {

    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("original_language")
    private String languageTV;
    @SerializedName("overview")
    private String overview;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String nameTV;
    @SerializedName("popularity")
    private String rateTV;
    @SerializedName("first_air_date")
    private String firstDate;
    @SerializedName("poster_path")
    private String posterTV;
    @SerializedName("backdrop_path")
    private String backdropTV;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getLanguageTV() {
        return languageTV;
    }

    public void setLanguageTV(String languageTV) {
        this.languageTV = languageTV;
    }



    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTV() {
        return nameTV;
    }

    public void setNameTV(String nameTV) {
        this.nameTV = nameTV;
    }

    public String getRateTV() {
        return rateTV;
    }

    public void setRateTV(String rateTV) {
        this.rateTV = rateTV;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getPosterTV() {
        return posterTV;
    }

    public void setPosterTV(String posterTV) {
        this.posterTV = posterTV;
    }

    public String getBackdropTV() {
        return backdropTV;
    }

    public void setBackdropTV(String backdropTV) {
        this.backdropTV = backdropTV;
    }

    public TVData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.voteCount);
        dest.writeString(this.languageTV);
        dest.writeString(this.overview);
        dest.writeList(this.genreIds);
        dest.writeInt(this.id);
        dest.writeString(this.nameTV);
        dest.writeString(this.rateTV);
        dest.writeString(this.firstDate);
        dest.writeString(this.posterTV);
        dest.writeString(this.backdropTV);
    }

    protected TVData(Parcel in) {
        this.voteCount = in.readInt();
        this.languageTV = in.readString();
        this.overview = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.id = in.readInt();
        this.nameTV = in.readString();
        this.rateTV = in.readString();
        this.firstDate = in.readString();
        this.posterTV = in.readString();
        this.backdropTV = in.readString();
    }

    public static final Creator<TVData> CREATOR = new Creator<TVData>() {
        @Override
        public TVData createFromParcel(Parcel source) {
            return new TVData(source);
        }

        @Override
        public TVData[] newArray(int size) {
            return new TVData[size];
        }
    };
}
