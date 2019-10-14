package com.example.favouritetvmovie.FavTV;


import java.util.ArrayList;
import java.util.List;

public class TVData {
    private int voteCount;
    private String languageTV;
    private String overview;
    private List<Integer> genreIds = new ArrayList<Integer>();
    private int id;
    private String nameTV;
    private String rateTV;
    private String firstDate;
    private String posterTV;
    private String backdropTV;

    public TVData( int id, String nameTV,  String firstDate, String posterTV, String overview) {
        this.id = id;
        this.nameTV = nameTV;
        this.firstDate = firstDate;
        this.posterTV = posterTV;
        this.overview= overview;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
}
