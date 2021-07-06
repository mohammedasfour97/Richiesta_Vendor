package com.richiestavendor.Orders.Presenter;

import com.richiestavendor.Orders.Contract;
import com.richiestavendor.Orders.Model.AddOrderModel;

public class AddOrderPresenter implements Contract.Add_Order.Presenter {

    private Contract.Add_Order.View view;
    private Contract.Add_Order.Model model;

    public AddOrderPresenter(Contract.Add_Order.View view) {
        this.view = view;
        model = new AddOrderModel();
    }

    @Override
    public void requestOrders(String id) {

    }
}
