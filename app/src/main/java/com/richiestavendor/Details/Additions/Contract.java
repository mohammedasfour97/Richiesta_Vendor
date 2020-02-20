package com.richiestavendor.Details.Additions;

import com.richiestavendor.ModelClasses.Detail;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Additions{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> additionsList);
                void onFailure(String error);
            }
            void getAdditions(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestAdditions(String id , String user_id);
        }

        interface View{

            void onFinished(List<Detail> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface AddAddition{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> branchesList);
                void onFailure(String error);
            }
            void getAddAddition(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestAddAddition(String id , String user_id);
        }

        interface View{

            void onFinished(String result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}


