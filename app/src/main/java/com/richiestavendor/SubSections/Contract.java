package com.richiestavendor.SubSections;

import com.richiestavendor.ModelClasses.SubSection;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface SubSections{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> SubSectionList);
                void onFailure(String error);
            }
            void getSubSections(onFinishedListener onFinishedListener, String id, String user_id);
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

