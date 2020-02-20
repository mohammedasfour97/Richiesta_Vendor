package com.richiestavendor.Branches.Presenter;

import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Branches.Model.BranchesModel;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BranchesPresenter implements Contract.Branches.Presenter {

    private Contract.Branches.View view;
    private Contract.Branches.Model model;

    public BranchesPresenter(Contract.Branches.View view) {
        this.view = view;
        model = new BranchesModel();
    }

    @Override
    public void requestBranches(String id, String user_id) {

        model.getBranches(new Contract.Branches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (branchesList.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    Branch _branch;
                    List<Branch> branches = new ArrayList<>();

                    for (HashMap<String , String> branch : branchesList){

                        _branch = new Branch(branch.get("ID"), branch.get("ID_Stores"), branch.get("CPName"), branch.get("ARName"), branch.get("ENName"),
                                branch.get("ARAddress"), branch.get("ENAddress"), branch.get("Longitude"), branch.get("Latitude"), branch.get("ID_Country"),
                                branch.get("ID_City"), branch.get("ID_Region"), branch.get("Tel"), branch.get("ad_state"), branch.get("us_state"),
                                branch.get("Ranking"), branch.get("ID_USER"), branch.get("DateEdite"), branch.get("DateCreate"));


                        branches.add(_branch);
                    }

                    view.onFinished(branches);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.equals("")) view.onFailure(R.string.no_branches);
                else view.onFailure(error);
            }
        } , id , user_id);
    }
}
