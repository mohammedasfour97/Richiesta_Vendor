package com.richiestavendor.RoomDB;

import com.richiestavendor.Store;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Store.class}, version = 1)
public abstract class StoreDatabase extends RoomDatabase {

    public abstract StoreDao storeDao();
}
