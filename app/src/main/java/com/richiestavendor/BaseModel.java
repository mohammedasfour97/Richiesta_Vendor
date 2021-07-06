package com.richiestavendor;

import com.richiestavendor.RoomDB.StoreDao;
import com.richiestavendor.RoomDB.StoreDatabase;

import javax.inject.Inject;

public class BaseModel {

    @Inject
    StoreDatabase storeDatabase;
    protected StoreDao storeDao;

    public BaseModel() {

        MyApplication.getUserComponent().inject(this);
        storeDao = storeDatabase.storeDao();
    }
}
