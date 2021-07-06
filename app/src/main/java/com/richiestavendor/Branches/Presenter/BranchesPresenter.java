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
    public void requestBranches(final String id) {

        model.getBranchesDetails(new Contract.Branches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (branchesList.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    Branch _branch ;
                    List<Branch> branches = new ArrayList<>();

                    for (int a = 0 ; a<branchesList.size() ; a++) {

                        _branch = new Branch();

                        _branch.setNameAR(branchesList.get(a).get("RKBranch_NameAR"));
                        _branch.setNameEN(branchesList.get(a).get("RKBranch_NameEN"));
                        _branch.setFeild(branchesList.get(a).get("RKBranch_Feild"));
                        _branch.setRK_Stores(branchesList.get(a).get("RKBranch_RK_Stores"));
                        _branch.setRK_branchNotes(branchesList.get(a).get("RKBranch_RK_branchNotes"));
                        _branch.setCreatedBy(branchesList.get(a).get("RKBranch_CreatedBy"));
                        _branch.setCreatedDate(branchesList.get(a).get("RKBranch_CreatedDate"));
                        _branch.setModifiedBy(branchesList.get(a).get("RKBranch_ModifiedBy"));
                        _branch.setModifiedDate(branchesList.get(a).get("RKBranch_ModifiedDate"));
                        _branch.setRK_StoresContact(branchesList.get(a).get("RKBranch_RK_StoresContact"));
                        _branch.setRK_Branch_ID(branchesList.get(a).get("ID"));
                        _branch.setRK_Address(branchesList.get(a).get("RKBranchDetails_Address"));
                        _branch.setRK_City(branchesList.get(a).get("RKBranchDetails_City"));
                        _branch.setRK_Country(branchesList.get(a).get("RKBranchDetails_Country"));
                        _branch.setRK_Phone(branchesList.get(a).get("RKBranchDetails_Phone"));
                        _branch.setRK_Cell(branchesList.get(a).get("RKBranchDetails_Cell"));
                        _branch.setRK_Email(branchesList.get(a).get("RKBranchDetails_Email"));
                        _branch.setRK_StoreNotes(branchesList.get(a).get("RKBranchDetails_StoreNotes"));
                        _branch.setCreatedBy(branchesList.get(a).get("RKBranchDetails_CreatedBy"));
                        _branch.setCreatedDate(branchesList.get(a).get("RKBranchDetails_CreatedDate"));
                        _branch.setModifiedBy(branchesList.get(a).get("RKBranchDetails_ModifiedBy"));
                        _branch.setModifiedDate(branchesList.get(a).get("RKBranchDetails_ModifiedDate"));
                        _branch.setCountry_name_en(branchesList.get(a).get("CountryNameEnglish"));
                        _branch.setCountry_name_ar(branchesList.get(a).get("CountryNameArabic"));
                        _branch.setCity_name_en(branchesList.get(a).get("CityNameEnglish"));
                        _branch.setCity_name_ar(branchesList.get(a).get("CityNameArabic"));
                        _branch.setRKBranchDetails_ID(branchesList.get(a).get("RKBranchDetails_ID"));


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
        }, id);
    }

    @Override
    public void requestDeleteBranch(String id, String id_details) {

        model.getDeleteBranch(new Contract.Branches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (!branchesList.isEmpty()){

                    view.HideProgress();

                    view.onFinished();
                }

                else onFailure("");
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.some_thng_err);
            }
        } , id , id_details);

    }
}
