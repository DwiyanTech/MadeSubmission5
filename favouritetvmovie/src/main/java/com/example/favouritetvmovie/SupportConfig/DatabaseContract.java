package com.example.favouritetvmovie.SupportConfig;

import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {

    public static final String TABLE_MOVIE = "movieDataDB";

    public static final String AUTHORMOVIE = "com.dwiyan.tvandmoviecatalogue.Provider";

    public static final Uri URI_MOVIE = Uri.parse("content://"+AUTHORMOVIE+"/"+TABLE_MOVIE);


    public static final String TABLE_TV= "tvDataDB";
    public static final String AUTHORTV = "com.dwiyan.tvandmoviecatalogue.Provider";
    public static final Uri URI_TV = Uri.parse("content://"+AUTHORTV+"/"+TABLE_TV);


   /* public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build(); */
}
