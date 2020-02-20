package com.richiestavendor.Details.Colors;

import com.richiestavendor.ModelClasses.Detail;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Colors{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> colorsList);
                void onFailure(String error);
            }
            void getColors(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestColors(String id , String user_id);
        }

        interface View{

            void onFinished(List<Detail> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface AddColor{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> colorsList);
                void onFailure(String error);
            }
            void getAddColor(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestAddColor(String id , String user_id);
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


