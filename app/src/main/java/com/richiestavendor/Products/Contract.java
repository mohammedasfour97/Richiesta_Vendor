package com.richiestavendor.Products;

import com.richiestavendor.ModelClasses.Product;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Products{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> productList);
                void onFailure(String error);
            }
            void getProducts(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestProducts(String id , String user_id);
        }

        interface View{

            void onFinished(List<Product> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface Add_Product{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String , String>> productList);
                void onFailure(String error);
            }
            void getProducts(onFinishedListener onFinishedListener , String id , String user_id);
        }

        interface Presenter{

            void requestProducts(String id , String user_id);
        }

        interface View{

            void onFinished(List<Product> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

