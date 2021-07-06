package com.richiestavendor.Additions;

import com.richiestavendor.ModelClasses.Addition;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Additions{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> AdditionList);
                void onFailure(String error);
            }
            void getColors(onFinishedListener onFinishedListener, String id );
            void getTags(onFinishedListener onFinishedListener, String id );
            void getSizes(onFinishedListener onFinishedListener, String id );
            void getCategories(onFinishedListener onFinishedListener, String id);
            void getAdditions(onFinishedListener onFinishedListener, String id);
            void getDeleteColor(onFinishedListener onFinishedListener, String id);
            void getDeleteCategory(onFinishedListener onFinishedListener, String id );
            void getDeleteSize(onFinishedListener onFinishedListener, String id );
            void getDeleteAddition(onFinishedListener onFinishedListener, String id);
        }

        interface Presenter{

            void requestColors(String id);
            void requestTags(String id);
            void requestSizes(String id);
            void requestCategories(String id);
            void requestAdditions(String id);
            void requestDeleteAddition(String id , String req);
        }

        interface View{

            void onFinished(List<Addition> result , String req);
            void onFinished(int m);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface Add_Addition{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> AdditionList);
                void onFailure(String error);
            }
            void getAddColor(onFinishedListener onFinishedListener, String id , Addition addition);
            void getAddCategory(onFinishedListener onFinishedListener, String id , Addition addition);
            void getAddSize(onFinishedListener onFinishedListener, String id , Addition addition);
            void getAddAddition(onFinishedListener onFinishedListener, String id , Addition addition);
        }

        interface Presenter{

            void requestAddAddition(Addition addition , String id , String req);
        }

        interface View{

            void onFinished(int message);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

