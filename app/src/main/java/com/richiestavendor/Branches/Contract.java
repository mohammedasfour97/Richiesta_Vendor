package com.richiestavendor.Branches;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.Product;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Branches{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> branchesList);
                void onFailure(String error);
            }
            void getBranches(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestBranches(String id , String user_id);
        }

        interface View{

            void onFinished(List<Branch> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface AddBranches{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> branchesList);
                void onFailure(String error);
            }
            void getAddBranches(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestAddBranches(String id , String user_id);
        }

        interface View{

            void onFinished(List<Branch> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

