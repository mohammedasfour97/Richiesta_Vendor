package com.richiestavendor.Products.Presenter;

import android.util.Log;

import com.richiestavendor.Branches.Model.BranchesModel;
import com.richiestavendor.ModelClasses.Branch;
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
    public void requestProducts(final String id, String user_id) {

        model.getProducts(new com.richiestavendor.Products.Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(final List<HashMap<String, String>> ProductsList) {

                if (ProductsList.isEmpty()) onFailure("");

                else {

                    final List<Product> Products = new ArrayList<>();
                    final Product[] _Product = new Product[1];

                    for (final HashMap<String , String> product : ProductsList) {

                        _Product[0] = new Product(product.get("RKPrdNameEN"), product.get("RKPrdNameAR"), product.get("RKPrdDescrEN"), product.get("RKPrdDescrAR"),
                                product.get("RKBranches_Id"), product.get("ProductNumber"), product.get("ProductUPC"), product.get("PurchaseUnitPrice"),
                                product.get("SalesUnitPrice"), product.get("ProductPic"), product.get("RK_Categories"), product.get("RK_Size"),
                                product.get("RK_Colors"), product.get("RK_ProductAdditionals"), product.get("IsAvailable"), product.get("RK_Notes"), product.get("CreatedBy"), product.get("CreatedDate"),
                                product.get("ModifiedBy"), product.get("ModifiedDate") , product.get("MeasurmentUnit") , product.get("MeasurmentUnitAr"),
                                product.get("ColorPrice") , product.get("AdditionalPrice"), product.get("SizePrice"));

                        _Product[0].setId(product.get("id"));
                        _Product[0].setRK_Size("");
                        _Product[0].setRK_Colors("");
                        _Product[0].setRK_Additins("");
                        _Product[0].setId(product.get("id"));

                        Products.add(_Product[0]);
                    }

                    for (int a=0 ; a<Products.size() ; a ++){

                        proAdditions(a , Products);

                    }

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


    private void proAdditions(final int a , final List<Product> Products){

        model.getProductsAdd(new Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> productList) {

                if (!productList.isEmpty()) {

                    for (HashMap<String, String> map : productList) {

                        if (!Products.get(a).getRK_Size().contains(map.get("SizeID")))
                            Products.get(a).setRK_Size(Products.get(a).getRK_Size() + map.get("SizeID") + ",");

                        if (!Products.get(a).getRK_Colors().contains(map.get("ColorsID")))
                            Products.get(a).setRK_Colors(Products.get(a).getRK_Colors()  + map.get("ColorsID") + ",");

                        if (!Products.get(a).getRK_Additins().contains(map.get("AdditionalID")))
                            Products.get(a).setRK_Additins(Products.get(a).getRK_Additins()  + map.get("AdditionalID") + ",");

                    }

                        view.HideProgress();
                        view.onFinished(Products);


                }

            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.some_thng_err);
            }
        }, Products.get(a).getId(), "");

    }

    @Override
    public void requestAddProducts(Product product) {

        model.getAddProducts(new com.richiestavendor.Products.Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> productList) {

                if (productList.isEmpty()) onFailure("");
                else {

                    view.HideProgress();

                    view.onFinished("ss");
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.some_thng_err);
            }
        } , product);
    }

    @Override
    public void requestModifyProducts(Product product) {

        model.getModifyProducts(new Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> productList) {

                if (productList.isEmpty()) onFailure("");
                else {

                    view.HideProgress();
                    view.onFinished(R.string.suc_mod_pro);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.fail_mod_pro);
            }
        } , product);
    }

    @Override
    public void requestBranches(String id) {

        new BranchesModel().getBranchesDetails(new com.richiestavendor.Branches.Contract.Branches.Model.onFinishedListener() {

            @Override
            public void onFinished(List<HashMap<String, String>> branchesList) {

                if (branchesList.isEmpty()) onFailure("");

                else {

                    view.HideProgress();

                    Branch _branch ;
                    List<Branch> branches = new ArrayList<>();

                    for (int a = 0 ; a<branchesList.size() ; a++) {

                        _branch = new Branch();

                        _branch.setNameAR(branchesList.get(a).get("RKBranch_NameAR"));
                        _branch.setNameEN(branchesList.get(a).get("RKBranch_NameEN"));
                        _branch.setFeild(branchesList.get(a).get("RKBranch_Feild"));
                        _branch.setRK_Stores(branchesList.get(a).get("RKBranch_RK_Stores"));
                        _branch.setRK_branchNotes(branchesList.get(a).get("RKBranch_RK_branchNotes"));
                        _branch.setCreatedBy(branchesList.get(a).get("RKBranch_CreatedBy"));
                        _branch.setCreatedDate(branchesList.get(a).get("RKBranch_CreatedDate"));
                        _branch.setModifiedBy(branchesList.get(a).get("RKBranch_ModifiedBy"));
                        _branch.setModifiedDate(branchesList.get(a).get("RKBranch_ModifiedDate"));
                        _branch.setRK_StoresContact(branchesList.get(a).get("RKBranch_RK_StoresContact"));
                        _branch.setRK_Branch_ID(branchesList.get(a).get("RKBranch_ID"));
                        _branch.setRK_Address(branchesList.get(a).get("RKBranchDetails_Address"));
                        _branch.setRK_City(branchesList.get(a).get("RKBranchDetails_City"));
                        _branch.setRK_Country(branchesList.get(a).get("RKBranchDetails_Country"));
                        _branch.setRK_Phone(branchesList.get(a).get("RKBranchDetails_Phone"));
                        _branch.setRK_Cell(branchesList.get(a).get("RKBranchDetails_Cell"));
                        _branch.setRK_Email(branchesList.get(a).get("RKBranchDetails_Email"));
                        _branch.setRK_StoreNotes(branchesList.get(a).get("RKBranchDetails_StoreNotes"));
                        _branch.setCreatedBy(branchesList.get(a).get("RKBranchDetails_CreatedBy"));
                        _branch.setCreatedDate(branchesList.get(a).get("RKBranchDetails_CreatedDate"));
                        _branch.setModifiedBy(branchesList.get(a).get("RKBranchDetails_ModifiedBy"));
                        _branch.setModifiedDate(branchesList.get(a).get("RKBranchDetails_ModifiedDate"));
                        _branch.setCountry_name_en(branchesList.get(a).get("CountryNameEnglish"));
                        _branch.setCountry_name_ar(branchesList.get(a).get("CountryNameArabic"));
                        _branch.setCity_name_en(branchesList.get(a).get("CityNameEnglish"));
                        _branch.setCity_name_ar(branchesList.get(a).get("CityNameArabic"));
                        _branch.setRKBranchDetails_ID(branchesList.get(a).get("RKBranchDetails_ID"));


                        branches.add(_branch);
                    }

                    view.onFinished(branches);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();

                if (error.equals("")) view.onFailure(R.string.no_branches);
                else view.onFailure(error);
            }
        }, id);
    }

    @Override
    public void requestUploadImg(byte[] bytes, String img_name) {

        model.getUploadImg(new Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> productList) {

                if (productList.isEmpty())onFailure("");
                else {

                    view.HideProgress();
                    if (productList.get(0).get("done").equals("Done")) view.onAddImgFinished();
                    else onFailure("");
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.err_upload_img);
            }
        },bytes , img_name);
    }

    @Override
    public void getrequestDeleteProducts(String id) {

        model.getDeleteProducts(new Contract.Products.Model.onFinishedListener() {
            @Override
            public void onFinished(List<HashMap<String, String>> productList) {

                if (productList.isEmpty()) onFailure("");
                else {

                    view.HideProgress();
                    view.onFinished(R.string.suc_del_pro);
                }
            }

            @Override
            public void onFailure(String error) {

                view.HideProgress();
                view.onFailure(R.string.fail_del_pro);
            }
        } , id);
    }

}

