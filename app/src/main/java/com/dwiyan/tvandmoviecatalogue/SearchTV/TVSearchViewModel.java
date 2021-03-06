package com.dwiyan.tvandmoviecatalogue.SearchTV;


import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.dwiyan.tvandmoviecatalogue.ApiConfig.ApiConfig;
import com.dwiyan.tvandmoviecatalogue.BuildConfig;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.ApiInterfaceTV;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVData;
import com.dwiyan.tvandmoviecatalogue.TvCatalogue.TVResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVSearchViewModel extends ViewModel {
    public MutableLiveData<List<TVData>> tvDataLive = new MutableLiveData<>();
    private final static String apikey = BuildConfig.TMDB_API_KEY;

    public void setTvDataLive(final Context context , String query){
        ApiInterfaceTV apiInterfaceTV = ApiConfig.getApiWeb().create(ApiInterfaceTV.class);
        Call<TVResponse> call = apiInterfaceTV.getSearchTV(query,apikey);
        call.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                final List<TVData> tvDataList = response.body().getResults();
                tvDataLive.setValue(tvDataList);
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {
                Toast.makeText(context,"Koneksi Terputus !!!",Toast.LENGTH_LONG).show();



            }
        });
    }

    LiveData<List<TVData>> getTvData(){
        return tvDataLive;
    }

}
