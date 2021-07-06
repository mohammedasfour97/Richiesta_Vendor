package com.richiestavendor.Settings.Presenters;

import com.richiestavendor.R;
import com.richiestavendor.RoomDB.StoreDao;
import com.richiestavendor.Settings.Contract;
import com.richiestavendor.Settings.Models.SettingsModel;
import com.richiestavendor.Store;

import java.util.List;

public class SettingsPresenter implements Contract.Settings.Presenter {

    private Contract.Settings.Model model;
    private Contract.Settings.View view;
    private StoreDao storeDao;

    public SettingsPresenter(Contract.Settings.View view) {
        super();
        this.model = new SettingsModel();
        this.view = view;
    }

    @Override
    public void requestStoreData() {

        model.getStoreData(new Contract.Settings.Model.onFinishedListener() {
            @Override
            public void onFinished(List<Store> StoresList) {

                if (StoresList.isEmpty()) onFailure("");
                else {

                    view.HideProgress();
                    view.onFinished(StoresList.get(0));
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.some_thng_err);
            }
        });
    }
}
