package com.example.favouritetvmovie.FavMovie;

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
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favouritetvmovie.Adapter.AdapterFavMovie;
import com.example.favouritetvmovie.R;
import com.example.favouritetvmovie.SupportConfig.DatabaseContract;
import com.example.favouritetvmovie.SupportConfig.LoadMovieCallbacks;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.favouritetvmovie.SupportConfig.MapHelper.mapCursorToArrayList;

public class FavouriteMovie extends Fragment implements LoadMovieCallbacks {
    private static final int LOADER_MOVIES = 1;
    private static final String TITLE = "title";
    AdapterFavMovie adapterFavMovie;
    RecyclerView recyclerView;
    private ProgressBar progressBar;
   // private FavouriteMovie.DataObserver dataObserver;
    private DataObserver movieObserver;
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite_tvmovie, viewGroup, false);

        return view;

    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {

        progressBar = view.findViewById(R.id.progressBar);


        progressBar = view.findViewById(R.id.progressBar);

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        movieObserver = new FavouriteMovie.DataObserver(handler, getContext());
        getContext().getContentResolver().registerContentObserver(DatabaseContract.URI_MOVIE, true, movieObserver);
        new FavouriteMovie.getDataMovie(getContext(), this).execute();

    }


    @Override
    public void postExecute(Cursor notes) {
        List<MovieData> listNotes = mapCursorToArrayList(notes);

        recyclerView = getView().findViewById(R.id.search_recycler);
        adapterFavMovie = new AdapterFavMovie(R.layout.tv_card, getContext(),listNotes);
        recyclerView.setAdapter(adapterFavMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.GONE);


    }


    public static class getDataMovie extends AsyncTask<Void,Void,Cursor>{

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMovieCallbacks> weakCallback;

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get().getContentResolver().query(DatabaseContract.URI_MOVIE, null, null, null, null);
        }

        public getDataMovie(Context context, LoadMovieCallbacks callback) {
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
            new getDataMovie(context,(LoadMovieCallbacks) context).execute();
        }
    }
}
