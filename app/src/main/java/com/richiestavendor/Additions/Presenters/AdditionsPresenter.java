package com.richiestavendor.Additions.Presenters;

import com.richiestavendor.Additions.Contract;
import com.richiestavendor.Additions.Models.AdditionsModel;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdditionsPresenter implements Contract.Additions.Presenter {

    private Contract.Additions.View view;
    private Contract.Additions.Model model;

    public AdditionsPresenter(Contract.Additions.View view) {
        this.view = view;
        this.model = new AdditionsModel();
    }


    @Override
    public void requestColors(String id) {

        model.getColors(new Contract.Additions.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> AdditionList) {

                if (AdditionList.isEmpty()) onFailure("");
                else {

                    List<Addition> additions = new ArrayList<>();

                    Addition addition;

                    for (HashMap<String, String> hashMap : AdditionList) {

                        addition = new Addition();

                        addition.setID(hashMap.get("ID"));
                        addition.setTitle(hashMap.get("ClrNameAR"));
                        addition.setColorHex(hashMap.get("ColorHex"));
                        addition.setClrNameAR(hashMap.get("ClrNameAR"));
                        addition.setClrNameEN(hashMap.get("ClrNameEN"));
                        addition.setClrNotes(hashMap.get("ClrNotes"));
                        addition.setCreatedBy(hashMap.get("CreatedBy"));
                        addition.setCreatedDate(hashMap.get("CreatedDate"));
                        addition.setModifiedBy(hashMap.get("ModifiedBy"));
                        addition.setModifiedDate(hashMap.get("ModifiedDate"));

                        additions.add(addition);
                    }

                    view.HideProgress();
                    view.onFinished(additions, "color");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        }, id);
    }

    @Override
    public void requestTags(String id) {

        model.getTags(new Contract.Additions.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> AdditionList) {

                if (AdditionList.isEmpty()) onFailure("");
                else {

                    List<Addition> additions = new ArrayList<>();

                    Addition addition;

                    for (HashMap<String, String> hashMap : AdditionList) {

                        addition = new Addition();

                        addition.setID(hashMap.get("ID"));
                        addition.setTitle(hashMap.get("TagNameAR"));
                        addition.setTagNameAR(hashMap.get("TagNameAR"));
                        addition.setTagNameEN(hashMap.get("TagNameEN"));
                        addition.setRK_Products(hashMap.get("RK_Products"));
                        addition.setTagNotes(hashMap.get("TagNotes"));
                        addition.setCreatedBy(hashMap.get("CreatedBy"));
                        addition.setCreatedDate(hashMap.get("CreatedDate"));
                        addition.setModifiedBy(hashMap.get("ModifiedBy"));
                        addition.setModifiedDate(hashMap.get("ModifiedDate"));

                        additions.add(addition);
                    }

                    view.HideProgress();
                    view.onFinished(additions, "tag");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        }, id);
    }

    @Override
    public void requestSizes(String id) {

        model.getSizes(new Contract.Additions.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> AdditionList) {

                if (AdditionList.isEmpty()) onFailure("");
                else {

                    List<Addition> additions = new ArrayList<>();

                    Addition addition;

                    for (HashMap<String, String> hashMap : AdditionList) {

                        addition = new Addition();

                        addition.setID(hashMap.get("ID"));
                        addition.setTitle(hashMap.get("SizeNameAR"));
                        addition.setSizeNameEN(hashMap.get("SizeNameEN"));
                        addition.setSizeNameAR(hashMap.get("SizeNameAR"));
                        addition.setSizeUnitType(hashMap.get("SizeUnitType"));
                        addition.setSizeNotes(hashMap.get("SizeNotes"));
                        addition.setCreatedBy(hashMap.get("CreatedBy"));
                        addition.setCreatedDate(hashMap.get("CreatedDate"));
                        addition.setModifiedBy(hashMap.get("ModifiedBy"));
                        addition.setModifiedDate(hashMap.get("ModifiedDate"));

                        additions.add(addition);
                    }

                    view.HideProgress();
                    view.onFinished(additions, "size");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        }, id);
    }

    @Override
    public void requestCategories(String id) {

        model.getCategories(new Contract.Additions.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> AdditionList) {

                if (AdditionList.isEmpty()) onFailure("");
                else {

                    List<Addition> additions = new ArrayList<>();

                    Addition addition;

                    for (HashMap<String, String> hashMap : AdditionList) {

                        addition = new Addition();

                        addition.setID(hashMap.get("ID"));
                        addition.setTitle(hashMap.get("CategoryNameAr"));
                        addition.setCategoryNameEN(hashMap.get("CategoryNameEN"));
                        addition.setCategoryNameAr(hashMap.get("CategoryNameAr"));
                        addition.setParentCategory(hashMap.get("ParentCategory"));
                        addition.setCategoryNotes(hashMap.get("CategoryNotes"));
                        addition.setCreatedBy(hashMap.get("CreatedBy"));
                        addition.setCreatedDate(hashMap.get("CreatedDate"));
                        addition.setModifiedBy(hashMap.get("ModifiedBy"));
                        addition.setModifiedDate(hashMap.get("ModifiedDate"));

                        additions.add(addition);
                    }

                    view.HideProgress();
                    view.onFinished(additions, "category");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        }, id);
    }

    @Override
    public void requestAdditions(String id) {

        model.getAdditions(new Contract.Additions.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> AdditionList) {

                if (AdditionList.isEmpty()) onFailure("");
                else {

                    List<Addition> additions = new ArrayList<>();

                    Addition addition;

                    for (HashMap<String, String> hashMap : AdditionList) {

                        addition = new Addition();

                        addition.setID(hashMap.get("id"));
                        addition.setAdditional(hashMap.get("Additional"));
                        addition.setAdditionalAr(hashMap.get("AdditionalAr"));

                        additions.add(addition);
                    }

                    view.HideProgress();
                    view.onFinished(additions, "add");
                }
            }

            @Override
            public void onFailure(String error) {

            }
        }, id);
    }

    @Override
    public void requestDeleteAddition(String id , String req) {

        switch (req){

            case "color" :
                model.getDeleteColor(new Contract.Additions.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_del_color);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_del_color);
                    }
                }, id);
                break;

            case "cat" :
                model.getDeleteCategory(new Contract.Additions.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_del_cat);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_del_cat);
                    }
                } , id);
                break;

            case "size" :
                model.getDeleteSize(new Contract.Additions.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_del_size);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_del_size);
                    }
                } , id);
                break;

            case "addition" :
                model.getDeleteAddition(new Contract.Additions.Model.onFinishedListener() {
                    @Override
                    public void onFinished(List<HashMap<String, String>> AdditionList) {

                        if (AdditionList.isEmpty()) onFailure("");
                        else {

                            view.HideProgress();
                            view.onFinished(R.string.suc_del_add);
                        }
                    }

                    @Override
                    public void onFailure(String error) {

                        view.HideProgress();
                        view.onFailure(R.string.failed_del_add);
                    }
                } , id);
                break;
        }
    }
}
