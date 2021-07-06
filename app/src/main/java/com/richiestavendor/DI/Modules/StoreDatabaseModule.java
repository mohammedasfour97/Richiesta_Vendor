package com.richiestavendor.DI.Modules;

import android.content.Context;

import com.richiestavendor.RoomDB.StoreDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class StoreDatabaseModule {

    private Context context;

    public StoreDatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public synchronized StoreDatabase getInstance() {

        return Room.databaseBuilder(context,
                    StoreDatabase.class, "store_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

    }