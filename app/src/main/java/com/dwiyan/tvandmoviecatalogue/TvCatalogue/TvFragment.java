package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.supportConfig.ItemClickSupport;

import java.util.List;

public class TvFragment extends Fragment {
private ProgressBar progressBar;
private Button btnShare;
private AdapterTV adapterTV;
public int idTV;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
          View rootView = inflater.inflate(R.layout.tv_view,container, false);
          return  rootView;
    }

    public void onViewCreated(@NonNull final View view , Bundle savedInstanceState){
            progressBar = view.findViewById(R.id.progressBar);
            final tvViewModel tvViewModel = ViewModelProviders.of(getActivity()).get(tvViewModel.class);
            tvViewModel.setTvDataLive(getActivity().getApplicationContext());
            progressBar.setVisibility(View.VISIBLE);
            tvViewModel.getTvData().observe(getActivity(), new Observer<List<TVData>>() {
                @Override
                public void onChanged(@Nullable final List<TVData> tvData) {
                    progressBar.setVisibility(View.GONE);
                    RecyclerView recyclerView = view.findViewById(R.id.search_recycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapterTV = new AdapterTV(tvData,R.layout.tv_card,getContext());
                    recyclerView.setAdapter(adapterTV);
                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {

                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            TVData dataMovieset = new TVData();
                            idTV = position + 1;
                            dataMovieset.setId(idTV);
                            dataMovieset.setNameTV(tvData.get(position).getNameTV());
                            dataMovieset.setOverview(tvData.get(position).getOverview());
                            dataMovieset.setFirstDate(tvData.get(position).getFirstDate());
                            dataMovieset.setPosterTV(tvData.get(position).getPosterTV());
                            dataMovieset.setRateTV(tvData.get(position).getRateTV());
                            dataMovieset.setBackdropTV(tvData.get(position).getBackdropTV());
                            Intent intent = new Intent(getActivity(), DetailTV.class);
                            intent.putExtra(DetailTV.EXTRA_PARCELABLE, dataMovieset);
                            startActivity(intent);
                        }
                    }); }
            });

    }



}
