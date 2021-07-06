package com.richiestavendor.DI.Modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context mApplication;

    public ContextModule(Context mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication;
    }
}
