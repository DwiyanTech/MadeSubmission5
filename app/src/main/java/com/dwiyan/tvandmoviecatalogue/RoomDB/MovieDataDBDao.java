package com.dwiyan.tvandmoviecatalogue.RoomDB;


import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDataDBDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    long insertMovieDB(MovieDataDB movieDataDB);
    @Query("SELECT * FROM movieDataDB")
    List<MovieDataDB> movieDataDBList();

    @Query("SELECT * FROM movieDataDB WHERE id = :id")
    List<MovieDataDB> findId(int id);

    @Query("DELETE FROM movieDataDB WHERE id = :id")
    void DeleteFavMovie(int id);

    @Query("SELECT * FROM movieDataDB")
    Cursor movieDataCursor();


    @Query("SELECT * FROM movieDataDB WHERE id = :id")
    Cursor findIdCursor(long id);
    @Delete
    void DeleteItem(MovieDataDB movieDataDB);

}
