package com.richiestavendor.Branches.View;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Branches.Presenter.AddBranchPresenter;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.IDName;
import com.richiestavendor.R;

import java.util.List;

import androidx.fragment.app.DialogFragment;

public class AddBranchFragment extends DialogFragment implements Contract.AddBranches.View {

    private EditText ar_name , en_name , field , branch_notes , adress , phone , cell , email , store_notes;
    private Spinner city , country;
    private Button save;
    private AddBranchPresenter addBranchPresenter;
    private String s_country , s_city , s_ar_name , s_en_name , s_field , s_branch_notes , s_adress , s_phone , s_cell , s_email , s_store_notes;
    private ProgressDialog progressDialog;
    private Branch branch;


    public AddBranchFragment() {
    }

    public AddBranchFragment(Branch branch) {
        this.branch = branch;
    }

    public static AddBranchFragment newInstance() {
        AddBranchFragment addBranchFragment = new AddBranchFragment();
        return addBranchFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_branch, container, false);

        initUI(view);
        setListeners(view);

        addBranchPresenter = new AddBranchPresenter(this);

        fillSpiners();

        if (branch!=null) setData();

        return view;

    }

    private void initUI(View view){

        ar_name = view.findViewById(R.id.ar_name);
        en_name = view.findViewById(R.id.e_name);
        field = view.findViewById(R.id.field);
        branch_notes = view.findViewById(R.id.branch_notes);
        save = view.findViewById(R.id.save);
        adress = view.findViewById(R.id.address);
        phone = view.findViewById(R.id.phone);
        cell = view.findViewById(R.id.cell);
        email = view.findViewById(R.id.email);
        store_notes = view.findViewById(R.id.stores_notes);
        city = view.findViewById(R.id.city);
        country = view.findViewById(R.id.country);
    }


    private void fillSpiners(){

        ShowProgress();

        addBranchPresenter.requestCC();
    }


    private void setData(){
        en_name.setText(branch.getNameEN());
        field.setText(branch.getFeild());
        branch_notes.setText(branch.getRK_branchNotes());
        adress.setText(branch.getRK_Address());
        phone.setText(branch.getRK_Phone());
        cell.setText(branch.getRK_Cell());
        email.setText(branch.getRK_Email());
        store_notes.setText(branch.getRK_StoreNotes());
        ar_name.setText(branch.getNameAR());

    }


    private void setListeners(View view){


        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                IDName idName = (IDName) parent.getItemAtPosition(position);
                s_country = idName.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                IDName idName = (IDName) parent.getItemAtPosition(position);
                s_city = idName.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getTexts()){

                    ShowProgress();

                    if (branch==null){

                        addBranchPresenter.requestAddBranches(s_ar_name, s_en_name,  s_field,  "100", branch.getRK_Stores()
                                , s_branch_notes, "CreatedBy",  "RK_Branch_ID",  s_adress, s_city, s_country,  s_phone, s_cell,  s_email,
                                s_store_notes, "Createdby",  "longitude",  "latidude");
                    }

                    else {

                        addBranchPresenter.requestModifyBranches(branch.getRK_Branch_ID() , s_ar_name, s_en_name,  s_field,  "100", "10", s_branch_notes,
                                "CreatedBy",  "RK_Branch_ID",  s_adress, s_city, s_country,  s_phone, s_cell,  s_email,
                                s_store_notes, "Createdby",  "longitude",  "latidude");
                    }



                }
            }
        });
    }



    private boolean getTexts(){


        s_ar_name = ar_name.getText().toString();
        s_en_name = en_name.getText().toString();
        s_field = field.getText().toString();
        s_branch_notes = branch_notes.getText().toString();
        s_adress = adress.getText().toString();
        s_phone = phone.getText().toString();
        s_cell = cell.getText().toString();
        s_email = email.getText().toString();
        s_store_notes = store_notes.getText().toString();

        if (TextUtils.isEmpty(s_ar_name)){

            ar_name.setError(getResources().getString(R.string.enter_ar_name));
            return false;
        }
        else if (TextUtils.isEmpty(s_en_name)){

            en_name.setError(getResources().getString(R.string.enter_en_name));
            return false;
        }

        else if (TextUtils.isEmpty(s_field)){

            field.setError(getResources().getString(R.string.enter_field));
            return false;
        }

        else if(TextUtils.isEmpty(s_branch_notes)){

            branch_notes.setError(getResources().getString(R.string.enter_bra_notes));
            return false;
        }

        else if(TextUtils.isEmpty(s_adress)){

            branch_notes.setError(getResources().getString(R.string.enter_address));
            return false;
        }

        else if(TextUtils.isEmpty(s_phone)){

            branch_notes.setError(getResources().getString(R.string.enter_phone));
            return false;
        }

        else return true;
    }

    @Override
    public void onFinished(int message) {

        Toast.makeText(getContext(), getResources().getString(message), Toast.LENGTH_SHORT).show();

        ((BranchesActivity)getActivity()).getBranches();
        dismiss();
    }

    @Override
    public void fillSpinners(List<IDName> idNameList , String req) {

        ArrayAdapter<IDName> spinnerArrayAdapter = new ArrayAdapter<IDName>(getContext(), android.R.layout.simple_spinner_item, idNameList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww

        if (req.equals("country")) country.setAdapter(spinnerArrayAdapter);
        else city.setAdapter(spinnerArrayAdapter);
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
