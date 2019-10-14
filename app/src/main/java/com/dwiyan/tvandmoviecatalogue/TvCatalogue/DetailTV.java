package com.dwiyan.tvandmoviecatalogue.TvCatalogue;

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
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailTV extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_PARCELABLE = "parcelabletv";
    FloatingActionButton fabDetail;
    TextView tvTitle,tvDesc,tvDate,tvRate;
    ImageView posterpath,backposterpath;
    AppDatabase appDatabase;
   public ImageButton unfavButton,favButton;

    public   Boolean favourite;
    public ArrayList<TVDataDB> TVDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_all);

        favButton = findViewById(R.id.fav_button);
        unfavButton = findViewById(R.id.unfav_button);
        tvTitle = findViewById(R.id.detail_title);
        tvDesc = findViewById(R.id.text_detail_overview);
        tvDate = findViewById(R.id.date);
        fabDetail = findViewById(R.id.fab);
        tvRate = findViewById(R.id.detail_rating);
        posterpath = findViewById(R.id.image_detail);
        backposterpath = findViewById(R.id.detail_backposter);
        final TVData tvData = getIntent().getParcelableExtra(EXTRA_PARCELABLE);
        tvRate.setText(tvData.getRateTV().toString()+" Ratings");
        tvTitle.setText(tvData.getNameTV());
        tvDesc.setText(tvData.getOverview());
        tvDate.setText(tvData.getFirstDate());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(16));
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/"+tvData.getPosterTV()).apply(requestOptions).override(500,750).into(posterpath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w342/"+tvData.getBackdropTV()).into(backposterpath);
        fabDetail.setOnClickListener(this);
        appDatabase = Room.databaseBuilder(this,AppDatabase.class,"tvDataDB").allowMainThreadQueries().build();
        TVDB = new ArrayList<>();
        TVDB.addAll(appDatabase.tvDataDBDao().findId(tvData.getId()));

        if(TVDB.size() > 0){
            favourite = true;
        } else {
            favourite = false;
        }

        setFavoriteIcon();


        unfavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TVDB.addAll(appDatabase.tvDataDBDao().tvDataDBList());
                TVDataDB tvDataDB = new TVDataDB();
                tvDataDB.setNameTV_Column(tvData.getNameTV());
                tvDataDB.setFirstDate_Column(tvData.getFirstDate());
                tvDataDB.setPosterTV_Column(tvData.getPosterTV());
                tvDataDB.setOverview_Column(tvData.getOverview());
                favourite = true;
                setFavoriteIcon();
                insertDataMovie(tvDataDB);
            }});


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unfavButton.setVisibility(View.VISIBLE);
                favButton.setVisibility(View.INVISIBLE);
                favourite = false;
                setFavoriteIcon();
                DeleteItem(tvData.getId());

            }
        });


    }
    @Override
    public void onClick(View v) {
        finish();
    }

    private void setFavoriteIcon() {
        if (favourite) {
            unfavButton.setVisibility(View.INVISIBLE);
            favButton.setVisibility(View.VISIBLE);
        } else {
            unfavButton.setVisibility(View.VISIBLE);
            favButton.setVisibility(View.INVISIBLE);
        }
    }


    @SuppressLint("StaticFieldLeak")
    private void insertDataMovie(final TVDataDB tvDataDB) {
        final AppDatabase database = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "tvDataDB").build();

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.tvDataDBDao().insertTVDB(tvDataDB);
            }

            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(getApplicationContext(), "Data berhasil Disimpan", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }


    public void DeleteItem(int position){
        appDatabase = Room.databaseBuilder(this, AppDatabase.class,"tvDataDB").allowMainThreadQueries().build();
        appDatabase.tvDataDBDao().DeleteFavTV(position);
        TVDB = new ArrayList<>();
        TVDB.addAll(appDatabase.tvDataDBDao().tvDataDBList());
        Toast.makeText(this,"Delete Data Berhasil ",Toast.LENGTH_LONG).show();
    }

}
