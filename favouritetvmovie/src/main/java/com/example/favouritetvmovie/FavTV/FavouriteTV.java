package com.example.favouritetvmovie.FavTV;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favouritetvmovie.Adapter.AdapterFavMovie;
import com.example.favouritetvmovie.Adapter.AdapterFavTV;
import com.example.favouritetvmovie.FavMovie.FavouriteMovie;
import com.example.favouritetvmovie.FavMovie.MovieData;
import com.example.favouritetvmovie.R;
import com.example.favouritetvmovie.SupportConfig.DatabaseContract;
import com.example.favouritetvmovie.SupportConfig.LoadMovieCallbacks;
import com.example.favouritetvmovie.SupportConfig.LoadTVCallbacks;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.example.favouritetvmovie.SupportConfig.MapHelper.mapCursorToArrayList;
import static com.example.favouritetvmovie.SupportConfig.MapHelper.mapCursorToArrayListTV;

public class FavouriteTV extends Fragment implements LoadTVCallbacks {
    private static final int LOADER_MOVIES = 1;
    private static final String TITLE = "title";
    AdapterFavTV adapterFavtv;
    RecyclerView recyclerView;
    private ProgressBar progressBar;
    private FavouriteTV.DataObserver dataObserver;
    private FavouriteTV.DataObserver movieObserver;
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite_tvmovie, viewGroup, false);

        return view;

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {

        progressBar = view.findViewById(R.id.progressBar);


        progressBar = view.findViewById(R.id.progressBar);

        HandlerThread handlerThread = new HandlerThread("DataObserver2");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        movieObserver = new FavouriteTV.DataObserver(handler, getContext());
        getContext().getContentResolver().registerContentObserver(DatabaseContract.URI_TV, true, movieObserver);
        new FavouriteTV.getDataTV(getContext(), this).execute();

    }


    @Override
    public void postExecute(Cursor notes) {
        List<TVData> listNotes = mapCursorToArrayListTV(notes);
        recyclerView = getView().findViewById(R.id.search_recycler);
        adapterFavtv = new AdapterFavTV(listNotes,R.layout.tv_card,getContext());
        recyclerView.setAdapter(adapterFavtv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Toast.makeText(getContext(),""+notes,Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);


    }


    public static class getDataTV extends AsyncTask<Void,Void,Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadTVCallbacks> weakCallback;

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(DatabaseContract.URI_TV,null,null,null,null,null);
        }

        public getDataTV(Context context, LoadTVCallbacks callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }
        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);

        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;
        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new FavouriteTV.getDataTV(context,(LoadTVCallbacks) context).execute();
        }
    }
}
