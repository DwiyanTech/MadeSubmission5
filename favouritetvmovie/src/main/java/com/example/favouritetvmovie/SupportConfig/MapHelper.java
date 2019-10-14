package com.example.favouritetvmovie.SupportConfig;

import android.database.Cursor;

import com.example.favouritetvmovie.FavMovie.MovieData;
import com.example.favouritetvmovie.FavTV.TVData;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;

public class MapHelper {
    public static List<MovieData> mapCursorToArrayList(Cursor notesCursor) {
       List<MovieData> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {


            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow("title"));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow("overview"));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow("release_date"));
            String posterPath = notesCursor.getString(notesCursor.getColumnIndexOrThrow("poster_path"));
            notesList.add(new MovieData(posterPath,title,description,date));
        }

        return notesList;
    }

    public static List<TVData> mapCursorToArrayListTV(Cursor notesCursor){
        List<TVData> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {

            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow("id"));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow("name"));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow("overview"));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow("first_air_date"));
            String posterPath = notesCursor.getString(notesCursor.getColumnIndexOrThrow("poster_path"));
            notesList.add(new TVData(id,title,date,posterPath,description));
        }
        return notesList;
    }
}
