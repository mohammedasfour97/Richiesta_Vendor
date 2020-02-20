package com.richiestavendor.SubSections.Presenter;

import com.richiestavendor.ModelClasses.SubSection;
import com.richiestavendor.R;
import com.richiestavendor.SubSections.Contract;
import com.richiestavendor.SubSections.Model.SubSectionsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubSectionsPresenter implements Contract.SubSections.Presenter {

    private Contract.SubSections.View view;
    private Contract.SubSections.Model model;

    public SubSectionsPresenter(Contract.SubSections.View view) {
        this.view = view;
        this.model = new SubSectionsModel();
    }

    @Override
    public void requestSubSections(String id, String user_id) {

        model.getSubSections(new Contract.SubSections.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> SubSectionList) {

                if (SubSectionList.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    SubSection _SubSection;
                    List<SubSection> SubSections = new ArrayList<>();

                    for (HashMap<String , String> SubSection : SubSectionList){

                        _SubSection = new SubSection(SubSection.get("ID"), SubSection.get("CPSubSections"), SubSection.get("ARSubSections"),
                                SubSection.get("ENSubSections"), SubSection.get("FRSubSections"), SubSection.get("Notes"), SubSection.get("Status"),
                                SubSection.get("Ranking"), SubSection.get("ARDescription"), SubSection.get("ENDescription"), SubSection.get("FRDescription"),
                                SubSection.get("ARImage"), SubSection.get("ENImage"), SubSection.get("FRImage"), SubSection.get("ID_Stores"),
                                SubSection.get("ID_Branches"), SubSection.get("ID_USER"), SubSection.get("DateEdite"), SubSection.get("DateCreate"));

                        SubSections.add(_SubSection);
                    }

                    view.onFinished(SubSections);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.equals("")) view.onFailure(R.string.no_SubSections);
                else view.onFailure(error);
            }
        } , id , user_id);
    }
}
