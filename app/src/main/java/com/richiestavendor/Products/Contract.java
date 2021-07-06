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
            void getAddProducts(onFinishedListener onFinishedListener , Product product);
            void getModifyProducts(onFinishedListener onFinishedListener , Product product);
            void getDeleteProducts(onFinishedListener onFinishedListener , String id);
            void getProductsAdd(onFinishedListener onFinishedListener, String id, String user_id);
            void getUploadImg(onFinishedListener onFinishedListener , byte[] bytes , String img_name);
        }

        interface Presenter{

            void requestProducts(String id , String user_id);
            void requestAddProducts(Product product);
            void requestModifyProducts(Product product);
            void requestBranches(String id);
            void requestUploadImg(byte[] bytes , String img_name);
            void getrequestDeleteProducts(String id);
        }

        interface View{

            void onFinished(List result);
            void onFinished(String result);
            void onFinished(int result);
            void onAddImgFinished();
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

