package com.richiestavendor.Additions.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.richiestavendor.Additions.Contract;
import com.richiestavendor.Additions.Presenters.AddAdditionPresenter;
import com.richiestavendor.Branches.Presenter.AddBranchPresenter;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.R;

import java.util.List;

import androidx.fragment.app.DialogFragment;

public class AddAdditionFragment extends DialogFragment implements Contract.Add_Addition.View {

    private EditText ar_name , en_name , notes;

    private Button save;
    private AddAdditionPresenter addAdditionPresenter;
    private String s_country , s_city , s_ar_name , s_en_name , s_field , s_branch_notes , s_adress , s_phone , s_cell , s_email , s_store_notes;
    private ProgressDialog progressDialog;
    private Addition addition;
    private String m;


    public AddAdditionFragment() {
    }

    public AddAdditionFragment(String m) {
        this.m = m;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_additions, container, false);

        initUI(view);
        setListeners(view);

        addAdditionPresenter = new AddAdditionPresenter(this);

        return view;

    }

    private void initUI(View view){

        ar_name = view.findViewById(R.id.ar_name);
        en_name = view.findViewById(R.id.e_name);
        notes = view.findViewById(R.id.notes);
        save = view.findViewById(R.id.save);
    }

    private void setListeners(View view){


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getTexts()){

                    addition = new Addition();

                    addition.setClrNameAR(s_ar_name);
                    addition.setClrNameEN(s_en_name);
                    addition.setClrNotes(s_branch_notes);
                    addition.setColorHex("");
                    addition.setSizeNameAR(s_ar_name);
                    addition.setSizeNameEN(s_en_name);
                    addition.setSizeNotes(s_branch_notes);
                    addition.setAdditionalAr(s_ar_name);
                    addition.setAdditional(s_en_name);
                    addition.setCategoryNameAr(s_ar_name);
                    addition.setCategoryNameEN(s_en_name);
                    addition.setCategoryNotes(s_branch_notes);
                    addition.setParentCategory("10");
                    ShowProgress();
                    addAdditionPresenter.requestAddAddition(addition , "10" , m);

                }
            }
        });
    }



    private boolean getTexts(){


        s_ar_name = ar_name.getText().toString();
        s_en_name = en_name.getText().toString();
        s_branch_notes = notes.getText().toString();

        if (TextUtils.isEmpty(s_ar_name)){

            ar_name.setError(getResources().getString(R.string.enter_ar_name));
            return false;
        }
        else if (TextUtils.isEmpty(s_en_name)){

            en_name.setError(getResources().getString(R.string.enter_en_name));
            return false;
        }

        else return true;
    }

    @Override
    public void onFinished(int message) {

        Toast.makeText(getContext(), getResources().getString(message), Toast.LENGTH_SHORT).show();
        ((AdditionsViewActivity)getActivity()).requestData();
        dismiss();
    }

    @Override
    public void onFailure(String error) {

        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int error) {

        Toast.makeText(getContext(), getResources().getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowProgress() {

        progressDialog = new ProgressDialog(getContext() , R.style.MyAlertDialogStyle);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void HideProgress() {

        progressDialog.hide();
    }
}

