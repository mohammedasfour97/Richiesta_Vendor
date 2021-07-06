package com.richiestavendor.Branches.Presenter;

import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Branches.Model.AddBranchModel;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.IDName;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddBranchPresenter implements Contract.AddBranches.Presenter {

    private Contract.AddBranches.View view;
    private Contract.AddBranches.Model model;

    public AddBranchPresenter(Contract.AddBranches.View view) {
        this.view = view;
        this.model = new AddBranchModel();
    }

    @Override
    public void requestAddBranches(String NameAR, String NameEN, String Feild, String RK_StoresContact, String RK_Stores,
                                   String RK_branchNotes, String CreatedBy, final String RK_Branch_ID, final String RK_Address,
                                   final String RK_City, final String RK_Country, final String RK_Phone, final String RK_Cell,
                                   final String RK_Email, final String RK_StoreNotes, final String Createdby, final String longitude,
                                   final String latidude) {

        model.getAddBranches(new Contract.AddBranches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (!branchesList.isEmpty()){

                    model.getAddBranchDetails(new Contract.AddBranches.Model.onFinishedListener() {
                        @Override
                        public void onFinished(List<HashMap<String, String>> branchesList) {

                            if (!branchesList.isEmpty()){

                                view.HideProgress();

                                view.onFinished(R.string.succ_add_bra);
                            }

                            else onFailure("");
                        }

                        @Override
                        public void onFailure(String error) {

                            view.HideProgress();
                            view.onFailure(R.string.bra_add_no_det);
                        }
                    } , branchesList.get(0).get("ID"),  RK_Address,  RK_City,  RK_Country,  RK_Phone, RK_Cell,  RK_Email,  RK_StoreNotes,
                            Createdby,  longitude,  latidude);
                }

                else onFailure("");
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                view.onFailure(R.string.some_thng_err);
            }
        } , NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, CreatedBy);
    }

    @Override
    public void requestModifyBranches(String id, String NameAR, String NameEN, String Feild, String RK_StoresContact,
                                      String RK_Stores, String RK_branchNotes, String CreatedBy, String RK_Branch_ID,
                                      String RK_Address, String RK_City, String RK_Country, String RK_Phone, String RK_Cell,
                                      String RK_Email, String RK_StoreNotes, String Createdby, String longitude, String latidude) {

        model.getModifyBranches(new Contract.AddBranches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (!branchesList.isEmpty()){

                    view.HideProgress();

                    view.onFinished(R.string.succ_modify_bra);
                }

                else onFailure("");
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.failed_modify_bra);

            }
        } , id , NameAR, NameEN, Feild, RK_StoresContact, RK_Stores, RK_branchNotes, CreatedBy);

    }

    @Override
    public void requestCC() {

        model.getCountries(new Contract.AddBranches.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                List<IDName> idNames;
                final IDName[] idName = new IDName[1];

                if (!branchesList.isEmpty()){

                    idNames = new ArrayList<>();

                    for (HashMap<String , String> map : branchesList){

                        idName[0] = new IDName();
                        idName[0].setId(map.get("ID"));
                        idName[0].setName_ar(map.get("ARCountry"));
                        idName[0].setName_en(map.get("ENCountry"));

                        idNames.add(idName[0]);
                    }

                    view.fillSpinners(idNames, "country");


                    model.getCities(new Contract.AddBranches.Model.onFinishedListener() {
                        @Override
                        public void onFinished(List<HashMap<String, String>> branchesList) {

                            List<IDName> idNames;

                            if (!branchesList.isEmpty()){

                                view.HideProgress();

                                idNames = new ArrayList<>();

                                for (HashMap<String , String> map : branchesList){

                                    idName[0] = new IDName();
                                    idName[0].setId(map.get("ID"));
                                    idName[0].setName_ar(map.get("CityAR"));
                                    idName[0].setName_en(map.get("CityEN"));

                                    idNames.add(idName[0]);
                                }
                                view.fillSpinners(idNames, "city");
                            }

                            else onFailure("");
                        }

                        @Override
                        public void onFailure(String error) {

                            view.HideProgress();

                            if (error.isEmpty()) view.onFailure(R.string.no_cities);
                            else view.onFailure(R.string.some_thng_err);
                        }
                    });
                }

                else onFailure("");
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.isEmpty()) view.onFailure(R.string.no_countries);
                else view.onFailure(R.string.some_thng_err);
            }
        });
    }
}
