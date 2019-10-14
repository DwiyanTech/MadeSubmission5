package com.dwiyan.tvandmoviecatalogue.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieDataDB.class,TVDataDB.class},version = 2,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDataDBDao movieDataDBDao();

    public abstract TVDataDBDao tvDataDBDao();

    public static AppDatabase sInstance;


    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ex")
                    .build();

        }
        return sInstance;
    }  //Sychronized With AppDatabse To


}
