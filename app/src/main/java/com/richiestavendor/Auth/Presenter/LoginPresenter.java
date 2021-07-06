package com.richiestavendor.Auth.Presenter;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.richiestavendor.Auth.Contract;
import com.richiestavendor.Auth.Models.LoginModel;
import com.richiestavendor.DI.Modules.StoreDatabaseModule;
import com.richiestavendor.MyApplication;
import com.richiestavendor.R;
import com.richiestavendor.RoomDB.StoreDao;
import com.richiestavendor.RoomDB.StoreDatabase;
import com.richiestavendor.Store;
import com.richiestavendor.TinyDB;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.Observer;

public class LoginPresenter implements Contract.Login.Presenter {

    private Contract.Login.View view;
    private Contract.Login.Model model;

    public LoginPresenter(Contract.Login.View view) {

        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void requestLogin(String number, String pass) {

        model.getLogin(new Contract.Login.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> results) {

                if (results.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    HashMap<String , String> r = results.get(0);

                    if (r.get("IsStoreAdmin").equals("true")) view.onViewAdmin(r.get("RK_Stores") ,r.get("RK_Branch") , r.get(""));
                    else view.onViewBranch(r.get("RK_Branch") , r.get("RK_Stores"));

                 /*   new TinyDB(c).putString("store_name" , r.get("RKStoreNameAr"));
                    new TinyDB(c).putString("logo" , r.get("RKStoreLogo"));*/
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.auth_error);
            }
        } , number , pass);
    }



}
