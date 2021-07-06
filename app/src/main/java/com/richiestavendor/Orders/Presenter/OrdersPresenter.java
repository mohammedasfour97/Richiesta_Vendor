package com.richiestavendor.Orders.Presenter;

import com.richiestavendor.Constants;
import com.richiestavendor.Order;
import com.richiestavendor.Orders.Model.OrdersModel;
import com.richiestavendor.Orders.Contract;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersPresenter implements Contract.Orders.Presenter {

    private Contract.Orders.View view;
    private Contract.Orders.Model model;

    public OrdersPresenter(Contract.Orders.View view) {
        this.view = view;
        model = new OrdersModel();
    }

    @Override
    public void requestOrders(String store_id , String branch_id) {

        model.getOrders(new Contract.Orders.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> OrdersList) {

                if (OrdersList.isEmpty()) onFailure("");

                else {

                    Constants.setEmpty(OrdersList);
                    view.HideProgress();

                    Order _Order;
                    List<Order> Orders = new ArrayList<>();

                    for (HashMap<String , String> Order : OrdersList){

                        _Order = new Order(Order.get("ID"), Order.get("SONumber"), Order.get("RK_Branch"), Order.get("BranchNameAR"),
                                Order.get("BranchNameEN"), Order.get("isMainBranch"), Order.get("RK_Products"), Order.get("ProdDetNameAR"),
                                Order.get("ColorUnitPrice"), Order.get("AdditionalNameAR"), Order.get("AdditionalUnitPrice"),
                                Order.get("SizeNameAR"), Order.get("SizeUnitPrice"), Order.get("SalesUnitPrice") , Order.get("Qty") ,
                                Order.get("TotalPrice"), Order.get("OrderNotes"), Order.get("OrderDate"), Order.get("IsComplete"),
                                Order.get("RK_User"), Order.get("RK_User1"), Order.get("IsRecieved"), Order.get("IsCustomerDelivered"),
                                Order.get("ItemPrice"), Order.get("ShippingRate"), Order.get("TotalwShipping"), Order.get("PaidAmount"),
                                Order.get("DisplayName"), Order.get("DisplayNameAR"));


                        Orders.add(_Order);
                    }

                    view.onFinished(Orders);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.equals("")) view.onFailure(R.string.no_orders);
                else view.onFailure(error);
            }
        } , store_id , branch_id);
    }
}

