package com.richiestavendor.Auth;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Login{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> results);
                void onFailure(String error);
            }
            void getLogin(onFinishedListener onFinishedListener , String number , String password);
        }

        interface Presenter{

            void requestLogin(String number , String pass);
        }

        interface View{

            void onViewAdmin(String store_id , String branch_id , String store_contact);
            void onViewBranch(String branch_id , String store_id);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}
