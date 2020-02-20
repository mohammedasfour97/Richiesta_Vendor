package com.richiestavendor;

import java.util.HashMap;

public class Contract {

    public interface Login{

        interface Model{

            interface onFinishedListener{

                void onFinished(HashMap<String , String> results);
                void onFailure(String error);
            }
            void getCheckIfNumberIsExist(onFinishedListener onFinishedListener , String number);
        }

        interface Presenter{

            void requestCheckIfNumberIsExist(String number);
        }

        interface View{

            void isExist();
            void isNotExist();
            void onFailure(String error);
            void onFailure(int error);


            void ShowProgress();
            void HideProgress();
        }
    }
}

