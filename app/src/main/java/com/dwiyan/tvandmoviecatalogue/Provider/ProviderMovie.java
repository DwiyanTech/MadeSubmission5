package com.dwiyan.tvandmoviecatalogue.Provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.dwiyan.tvandmoviecatalogue.RoomDB.AppDatabase;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDB;
import com.dwiyan.tvandmoviecatalogue.RoomDB.MovieDataDBDao;
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDB;
import com.dwiyan.tvandmoviecatalogue.RoomDB.TVDataDBDao;


import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ProviderMovie extends ContentProvider {

    public static final int CODE_TV_DIR = 3;
    public static final int CODE_TV_ITEM = 4;
    public static final String TABLE_TV= "tvDataDB";
    public static final String TABLE_MOVIE = "movieDataDB";
    public static final String AUTHORMOVIE = "com.dwiyan.tvandmoviecatalogue.Provider";
    public static final Uri URI_MOVIE = Uri.parse("content://"+AUTHORMOVIE+"/"+TABLE_MOVIE);
    public static final int CODE_MOVIE_DIR = 1;
    public static final int CODE_MOVIE_ITEM = 2;
    public static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    public  AppDatabase appDatabase;

    static  {


        MATCHER.addURI(AUTHORMOVIE,TABLE_MOVIE,CODE_MOVIE_DIR);
        MATCHER.addURI(AUTHORMOVIE,TABLE_MOVIE+"/*",CODE_MOVIE_ITEM);
        MATCHER.addURI(AUTHORMOVIE,TABLE_TV,CODE_TV_DIR);
        MATCHER.addURI(AUTHORMOVIE,TABLE_TV+"/*",CODE_TV_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);

            switch (MATCHER.match(uri)) {

                case CODE_MOVIE_DIR :
                if (code == CODE_MOVIE_DIR || code == CODE_MOVIE_ITEM) {
                    final Context context = getContext();
                    if (context == null) {
                        return null;
                    }
                    appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "MovieDataDB").allowMainThreadQueries().build();

                    MovieDataDBDao Movie = appDatabase.movieDataDBDao();
                    final Cursor cursor;
                    if (code == CODE_MOVIE_ITEM) {
                        cursor = Movie.findIdCursor(ContentUris.parseId(uri));
                        System.out.println(cursor);
                    } else {
                        cursor = Movie.movieDataCursor();
                        System.out.println(cursor);
                    }
                    cursor.setNotificationUri(context.getContentResolver(), uri);
                    return cursor;
                } else {

                    throw new IllegalArgumentException(new IllegalArgumentException().getMessage());
                }


                case CODE_TV_DIR :


                    if (code == CODE_TV_DIR || code == CODE_TV_ITEM) {
                        final Context context = getContext();
                        if (context == null) {
                            return null;
                        }
                        appDatabase =  Room.databaseBuilder(getContext(), AppDatabase.class,TABLE_TV).allowMainThreadQueries().build();

                        TVDataDBDao tv = appDatabase.tvDataDBDao();
                        final Cursor cursor;
                        if (code == CODE_TV_DIR) {
                            cursor = tv.tvDataDBCursor();
                            System.out.println(cursor);
                        } else {
                            cursor = tv.findIdCursor(ContentUris.parseId(uri));
                            System.out.println(cursor);
                        }
                        cursor.setNotificationUri(context.getContentResolver(), uri);
                        return cursor;
                    } else {
                        throw new IllegalArgumentException("Unknown URI: " + uri);
                    }

                    default:
                        throw new IllegalArgumentException("Unknown URI: " + uri);

            }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (MATCHER.match(uri)) {

            case CODE_MOVIE_DIR :
            switch (MATCHER.match(uri)) {
                case CODE_MOVIE_DIR:
                    return "vnd.android.cursor.dir/" + AUTHORMOVIE + "." + TABLE_MOVIE;
                case CODE_MOVIE_ITEM:
                    return "vnd.android.cursor.item/" + AUTHORMOVIE + "." + TABLE_MOVIE;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);

            }

            case CODE_TV_DIR :

                switch (MATCHER.match(uri)) {
                    case CODE_MOVIE_DIR:
                        return "vnd.android.cursor.dir/" + AUTHORMOVIE + "." + TABLE_TV;
                    case CODE_MOVIE_ITEM:
                        return "vnd.android.cursor.item/" + AUTHORMOVIE + "." + TABLE_TV;
                    default:
                        throw new IllegalArgumentException("Unknown URI: " + uri);

                }

                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);


        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {


        switch (MATCHER.match(uri)) {

            case CODE_MOVIE_DIR:
                appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "MovieDataDB").allowMainThreadQueries().build();

                switch (MATCHER.match(uri)) {
                    case CODE_MOVIE_DIR:
                        final Context context = getContext();
                        if (context == null) {
                            return null;
                        }
                        final long id = appDatabase.movieDataDBDao()
                                .insertMovieDB(MovieDataDB.fromContentValues(contentValues));
                        context.getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    case CODE_MOVIE_ITEM:
                        throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
                    default:
                        throw new IllegalArgumentException("Unknown URI: " + uri);
                }

            case CODE_TV_DIR:

                appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "MovieDataDB").allowMainThreadQueries().build();

                switch (MATCHER.match(uri)) {
                    case CODE_TV_DIR:
                        final Context context = getContext();
                        if (context == null) {
                            return null;
                        }
                        final long id = appDatabase.tvDataDBDao()
                                .insertTVDB(TVDataDB.fromContentValues(contentValues));
                        context.getContentResolver().notifyChange(uri, null);
                        return ContentUris.withAppendedId(uri, id);
                    case CODE_TV_ITEM:
                        throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
                    default:
                        throw new IllegalArgumentException("Unknown URI: " + uri);



                }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

    }
    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(


            @NonNull final ArrayList<ContentProviderOperation> operations) {
        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }
        final AppDatabase database = AppDatabase.getInstance(context);
        return database.runInTransaction(new Callable<ContentProviderResult[]>() {
            @Override
            public ContentProviderResult[] call() throws OperationApplicationException {
                return ProviderMovie.super.applyBatch(operations);
            }
        });
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
