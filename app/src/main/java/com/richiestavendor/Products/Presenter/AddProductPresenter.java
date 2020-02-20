package com.richiestavendor.Products.Presenter;

import com.richiestavendor.Products.Contract;
import com.richiestavendor.Products.Model.AddProductModel;

public class AddProductPresenter implements Contract.Add_Product.Presenter {

    private Contract.Add_Product.View view;
    private Contract.Add_Product.Model model;

    public AddProductPresenter(Contract.Add_Product.View view) {
        this.view = view;
        model = new AddProductModel();
    }

    @Override
    public void requestProducts(String id, String user_id) {

    }
}
