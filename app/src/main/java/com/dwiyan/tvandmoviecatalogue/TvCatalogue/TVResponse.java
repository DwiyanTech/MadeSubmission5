package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

import com.dwiyan.tvandmoviecatalogue.MovieCatalog.MovieData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    public List<TVData> results;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TVData> getResults() {
        return results;
    }

    public void setResults(List<TVData> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
