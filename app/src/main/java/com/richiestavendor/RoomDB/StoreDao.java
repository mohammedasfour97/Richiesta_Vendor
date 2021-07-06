package com.richiestavendor.RoomDB;

import com.richiestavendor.Store;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StoreDao {

    @Query("SELECT * FROM store_table")
    List<Store> getStoreDetails();

    @Insert
    void insert(Store store);

    @Update
    void update(Store note);

    @Delete
    void delete(Store store);
}
