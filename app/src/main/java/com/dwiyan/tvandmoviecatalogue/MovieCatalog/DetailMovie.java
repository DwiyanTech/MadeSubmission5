package com.dwiyan.tvandmoviecatalogue.MovieCatalog;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.dwiyan.tvandmoviecatalogue.R;
import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailMovie extends AppCompatActivity {
    public static final String EXTRA_PARCELABLE = "parcelable_extra";
    FloatingActionButton fabDetail;
    TextView tvTitle, tvDesc, tvDate, tvRate;
    ImageButton favButton, unfavButton;
        private Boolean favourite;

    ImageView posterpath, backposterpath;
    private AppDatabase appDatabase;
    private ArrayList<MovieDataDB> movieDataDBList;
    public int idSave;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_all);

        tvTitle = findViewById(R.id.detail_title);
        tvDesc = findViewById(R.id.text_detail_overview);
        favButton = findViewById(R.id.fav_button);
        tvDate = findViewById(R.id.date);
        tvRate = findViewById(R.id.detail_rating);
        posterpath = findViewById(R.id.image_detail);
        backposterpath = findViewById(R.id.detail_backposter);
        fabDetail = findViewById(R.id.fab);
        unfavButton = findViewById(R.id.unfav_button);
        final MovieData movieData = getIntent().getParcelableExtra(EXTRA_PARCELABLE);
        tvRate.setText(movieData.getPopularity().toString() + " Ratings");
        tvTitle.setText(movieData.getTitle());
        tvDesc.setText(movieData.getOverview());
        tvDate.setText(movieData.getReleaseDate());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/" + movieData.getPosterPath()).apply(requestOptions).override(500, 750).into(posterpath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/" + movieData.getBackdropPath()).into(backposterpath);
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "MovieDataDB").allowMainThreadQueries().build();

        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().findId(movieData.getId()));
        //Jika Size nya Lebih Dari 0
        if(movieDataDBList.size() > 0){
         favourite = true;
        } else {
           favourite = false;
        }

        setFavoriteIcon();


        fabDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        unfavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());

                MovieDataDB movieDataDB = new MovieDataDB();
                movieDataDB.setTitle_column(movieData.getTitle());
                movieDataDB.setReleaseDate_column(movieData.getReleaseDate());
                movieDataDB.setPosterPath_column(movieData.getPosterPath());
                movieDataDB.setOverview_column(movieData.getOverview());
                movieDataDB.setFavourite_column(true);
                favourite = true;
                movieDataDB.setId(movieData.getId());
                setFavoriteIcon();
                insertDataMovie(movieDataDB);


            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unfavButton.setVisibility(View.VISIBLE);
                favButton.setVisibility(View.INVISIBLE);
            DeleteItem(movieData.getId());


            }
        });
        setFavoriteIcon();



    }


    // AsyncTask For Insert Data To DB
    @SuppressLint("StaticFieldLeak")
    private void insertDataMovie(final MovieDataDB movieDataDB) {
        final AppDatabase database = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "MovieDataDB").build();

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.movieDataDBDao().insertMovieDB(movieDataDB);
            }

            protected void onPostExecute(Long status) {


                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(getApplicationContext(), "Data berhasil Disimpan", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    // Favourite Icon

    private void setFavoriteIcon() {
        if (favourite) {
        unfavButton.setVisibility(View.INVISIBLE);
        favButton.setVisibility(View.VISIBLE);
        } else {
            unfavButton.setVisibility(View.VISIBLE);
            favButton.setVisibility(View.INVISIBLE);
        }


    }


    // Set Delete Item
    public void DeleteItem(int position){
        appDatabase = Room.databaseBuilder(this, AppDatabase.class,"MovieDataDB").allowMainThreadQueries().build();
        appDatabase.movieDataDBDao().DeleteFavMovie(position);
        movieDataDBList = new ArrayList<>();
        movieDataDBList.addAll(appDatabase.movieDataDBDao().movieDataDBList());
        Toast.makeText(this,"Delete Data Berhasil ",Toast.LENGTH_LONG).show();
    }


    // Setting Date Format Config
    private String dateFormat(String oldDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = dateFormat.parse(oldDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat newFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String finalDate = newFormat.format(myDate);

        return finalDate;

    }
}

