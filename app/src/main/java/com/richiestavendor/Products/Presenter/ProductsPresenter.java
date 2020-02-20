package com.richiestavendor.Products.Presenter;

import com.richiestavendor.Products.Model.ProductsModel;
import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.Products.Contract;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsPresenter implements Contract.Products.Presenter {

    private com.richiestavendor.Products.Contract.Products.View view;
    private com.richiestavendor.Products.Contract.Products.Model model;

    public ProductsPresenter(com.richiestavendor.Products.Contract.Products.View view) {
        this.view = view;
        model = new ProductsModel();
    }

    @Override
    public void requestProducts(String id, String user_id) {

        model.getProducts(new com.richiestavendor.Products.Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> ProductsList) {

                if (ProductsList.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    Product _Product;
                    List<Product> Products = new ArrayList<>();

                    for (HashMap<String , String> product : ProductsList){

                        _Product = new Product(product.get("ID"), product.get("ID_Stores"), product.get("ID_Branches"), product.get("ID_SubSections"),
                                product.get("CPName"), product.get("ARName"), product.get("ENName"), product.get("FRName"), product.get("ARSummary"),
                                product.get("ENSummary"), product.get("FRSummary"), product.get("ARDetails"), product.get("ENDetails"),
                                product.get("FRDetails"), product.get("ARUse"), product.get("ENUse"), product.get("FRUse"), product.get("ARImage"),
                                product.get("ENImage"), product.get("FRImage"), product.get("Code"), product.get("Status"), product.get("sales"),
                                product.get("Views"), product.get("Searches"), product.get("Comments"), product.get("Ratings"), product.get("AvgRating"),
                                product.get("Notes"), product.get("Ranking"), product.get("Ranking"), product.get("DateEdite"), product.get("DateCreate"));


                        Products.add(_Product);
                    }

                    view.onFinished(Products);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.equals("")) view.onFailure(R.string.no_products);
                else view.onFailure(error);
            }
        } , id , user_id);
    }
}

