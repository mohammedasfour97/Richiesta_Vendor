package com.richiestavendor.Branches;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.IDName;
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
            void getBranches(onFinishedListener onFinishedListener , String id);
            void getBranchesDetails(onFinishedListener onFinishedListener , String id);
            void getDeleteBranch(onFinishedListener onFinishedListener , String id , String id_details);
        }

        interface Presenter{

            void requestBranches(String id );
            void requestDeleteBranch(String id , String id_details);
        }

        interface View{

            void onFinished(List<Branch> result);
            void onFinished();
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

            void getAddBranches(onFinishedListener onFinishedListener , String NameAR, String NameEN, String Feild, String RK_StoresContact,
                                String RK_Stores, String RK_branchNotes, String CreatedBy);
            void getModifyBranches(onFinishedListener onFinishedListener , String id , String NameAR, String NameEN, String Feild, String RK_StoresContact,
                                String RK_Stores, String RK_branchNotes, String CreatedBy);
            void getAddBranchDetails(onFinishedListener onFinishedListener , String RK_Branch_ID, String RK_Address, String	RK_City, String	RK_Country, String	RK_Phone, String RK_Cell,
                                     String	RK_Email, String RK_StoreNotes, String Createdby , String longitude , String latidude);
            void getCountries(onFinishedListener onFinishedListener);
            void getCities(onFinishedListener onFinishedListener);
        }

        interface Presenter{

            void requestAddBranches(String NameAR, String NameEN, String Feild, String RK_StoresContact, String RK_Stores, String RK_branchNotes,
                                    String CreatedBy , String RK_Branch_ID, String  RK_Address, String 	RK_City, String 	RK_Country, String 	RK_Phone,
                                    String 	RK_Cell, String 	RK_Email, String 	RK_StoreNotes, String 	Createdby  , String longitude ,
                                    String latidude);
            void requestModifyBranches(String id , String NameAR, String NameEN, String Feild, String RK_StoresContact, String RK_Stores, String RK_branchNotes,
                                    String CreatedBy , String RK_Branch_ID, String  RK_Address, String 	RK_City, String 	RK_Country, String 	RK_Phone,
                                    String 	RK_Cell, String 	RK_Email, String 	RK_StoreNotes, String 	Createdby  , String longitude , String latidude);

            void requestCC();
        }

        interface View{

            void onFinished(int message);
            void fillSpinners(List<IDName> idNameList , String req);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

