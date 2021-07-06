package com.richiestavendor.DI;


import com.richiestavendor.Auth.Presenter.LoginPresenter;
import com.richiestavendor.BaseModel;
import com.richiestavendor.DI.Modules.ContextModule;
import com.richiestavendor.DI.Modules.StoreDatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, StoreDatabaseModule.class})
public interface StoreComponent {

    void inject(BaseModel baseModel);
}
