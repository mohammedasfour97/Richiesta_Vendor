package com.richiestavendor.Settings;

import com.richiestavendor.ModelClasses.SubSection;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Settings{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> SubSectionList);
                void onFailure(String error);
            }
            void getSubSections(com.richiestavendor.SubSections.Contract.SubSections.Model.onFinishedListener onFinishedListener, String id, String user_id);
        }

        interface Presenter{

            void requestSubSections(String id, String user_id);
        }

        interface View{

            void onFinished(List<SubSection> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}
