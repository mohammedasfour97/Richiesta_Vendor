package com.richiestavendor.Additions.Presenters;

import com.richiestavendor.Additions.Contract;
import com.richiestavendor.Additions.Models.AddAdditionModel;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.R;

import java.util.HashMap;
import java.util.List;

public class AddAdditionPresenter implements Contract.Add_Addition.Presenter {

    private Contract.Add_Addition.Model model;
    private Contract.Add_Addition.View view;

    public AddAdditionPresenter(Contract.Add_Addition.View view) {
        this.view = view;
        model = new AddAdditionModel();
    }

    @Override
    public void requestAddAddition(Addition addition, String id, String req) {

        switch (req){

            case "color" :
                model.getAddColor(new Contract.Add_Addition.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_add_color);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_add_color);
                    }
                } , id , addition);
                break;

            case "cat" :
                model.getAddCategory(new Contract.Add_Addition.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_add_cat);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_add_cat);
                    }
                } , id , addition);
                break;

            case "size" :
                model.getAddSize(new Contract.Add_Addition.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_add_size);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_add_size);
                    }
                } , id , addition);
                break;

            case "addition" :
                model.getAddAddition(new Contract.Add_Addition.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_add_add);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_add_add);
                    }
                } , id , addition);
                break;
        }
    }
}
