package com.richiestavendor.Orders;

import com.richiestavendor.Order;

import java.util.HashMap;
import java.util.List;

public class Contract {

    public interface Orders{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> OrderList);
                void onFailure(String error);
            }
            void getOrders(onFinishedListener onFinishedListener, String store_id , String branch_id);
        }

        interface Presenter{

            void requestOrders(String store_id , String branch_id);
        }

        interface View{

            void onFinished(List<Order> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }

    public interface Add_Order{

        interface Model{

            interface onFinishedListener{

                void onFinished(List<HashMap<String, String>> OrderList);
                void onFailure(String error);
            }
            void getOrders(onFinishedListener onFinishedListener, String id);
        }

        interface Presenter{

            void requestOrders(String id);
        }

        interface View{

            void onFinished(List<Order> result);
            void onFailure(String error);
            void onFailure(int error);

            void ShowProgress();
            void HideProgress();
        }
    }
}

