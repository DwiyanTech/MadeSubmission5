package com.dwiyan.tvandmoviecatalogue.RoomDB;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TVDataDBDao {


    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    long insertTVDB(TVDataDB tvDataDB);
    @Query("SELECT * FROM tvDataDB")
    List<TVDataDB> tvDataDBList();

    @Query("SELECT * FROM tvDataDB WHERE id = :id")
    List<TVDataDB> findId(int id);

    @Query("DELETE FROM tvDataDB WHERE id = :id")
    void DeleteFavTV(int id);

    @Query("SELECT * FROM tvDataDB")
    Cursor tvDataDBCursor();

    @Query("SELECT * FROM tvDataDB WHERE id = :id")
    Cursor findIdCursor(long id);

    @Delete
    void DeleteItem(TVDataDB tvDataDB);


}
