package com.richiestavendor.Settings.Models;

import android.os.AsyncTask;

import com.richiestavendor.Auth.Models.LoginModel;
import com.richiestavendor.BaseModel;
import com.richiestavendor.Settings.Contract;
import com.richiestavendor.Store;

import java.util.List;

public class SettingsModel extends BaseModel implements Contract.Settings.Model {

    @Override
    public void getStoreData(onFinishedListener onFinishedListener) {

        new SelectUsersAsyncTask(onFinishedListener).execute();
    }

    private class SelectUsersAsyncTask extends AsyncTask<Void, Void, List<Store>> {

        private onFinishedListener onFinishedListener;

        public SelectUsersAsyncTask(Contract.Settings.Model.onFinishedListener onFinishedListener) {
            this.onFinishedListener = onFinishedListener;
        }

        @Override
        protected List<Store> doInBackground(Void... voids) {
            return storeDao.getStoreDetails();
        }

        @Override
        protected void onPostExecute(List<Store> stores) {

            try {

                onFinishedListener.onFinished(stores);

            }catch (Exception e){

                onFinishedListener.onFailure(e.getMessage());
            }
        }
    }
}
