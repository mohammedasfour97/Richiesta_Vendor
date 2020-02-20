package com.richiestavendor.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.Products.Contract;
import com.richiestavendor.R;

import java.util.List;

import androidx.fragment.app.DialogFragment;

public class AddProduct extends DialogFragment implements Contract.Add_Product.View {

    private EditText ar_name , en_name , fr_name , ar_summary , en_summary , fr_summary , ar_details , en_details , fr_details;
    private Button save;

    public AddProduct() {
        // Required empty public constructor
    }

    public static AddProduct newInstance() {
        AddProduct  fragment = new AddProduct();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_list_add, container, false);

        initUI(view);
        setListeners(view);

        return view;

    }

    private void initUI(View view){

        ar_name = view.findViewById(R.id.ar_name);
        en_name = view.findViewById(R.id.e_name);
        ar_summary = view.findViewById(R.id.ar_summary);
        en_summary = view.findViewById(R.id.english_summary);
        en_details = view.findViewById(R.id.e_details);
        ar_details = view.findViewById(R.id.ar_details);
        save = view.findViewById(R.id.save);

    }

    private void setListeners(View view){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getTexts()){


                }
            }
        });
    }

    private boolean getTexts(){

        boolean b = true;

        if (TextUtils.isEmpty(ar_name.getText()) || TextUtils.isEmpty(en_name.getText())||
                TextUtils.isEmpty(ar_summary.getText()) || TextUtils.isEmpty(en_summary.getText()) ||
        TextUtils.isEmpty(en_details.getText()) || TextUtils.isEmpty(ar_details.getText())) b = false;

        return b ;
    }

    @Override
    public void onFinished(List<Product> result) {

    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onFailure(int error) {

    }

    @Override
    public void ShowProgress() {

    }

    @Override
    public void HideProgress() {

    }
}
