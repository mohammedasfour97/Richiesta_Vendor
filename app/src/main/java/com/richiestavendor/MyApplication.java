package com.richiestavendor;

import android.app.Application;

import com.richiestavendor.DI.DaggerStoreComponent;
import com.richiestavendor.DI.Modules.ContextModule;
import com.richiestavendor.DI.Modules.StoreDatabaseModule;
import com.richiestavendor.DI.StoreComponent;


public class MyApplication extends Application {

    public static StoreComponent storeComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (storeComponent== null) {
            storeComponent = DaggerStoreComponent.builder()
                    .contextModule(new ContextModule(getApplicationContext()))
                    .storeDatabaseModule(new StoreDatabaseModule(getApplicationContext()))
                    .build();
        }

    }

    public static StoreComponent getUserComponent() {
        return storeComponent;
    }


}
