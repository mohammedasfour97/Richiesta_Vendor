package com.richiestavendor.Auth.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.richiestavendor.Auth.Contract;
import com.richiestavendor.Auth.Presenter.LoginPresenter;
import com.richiestavendor.BaseActivity;
import com.richiestavendor.R;
import com.richiestavendor.TinyDB;
import com.richiestavendor.View.MainActivity;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity implements Contract.Login.View {

    private EditText phone ;
    private ShowHidePasswordEditText pass;
    private LoginPresenter loginPresenter;
    private Button login;
    private TinyDB tinyDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        setListener();

        tinyDB  =new TinyDB(this);

        loginPresenter = new LoginPresenter(this);
    }


    private void initUI(){

        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
    }


    private void setListener(){

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(getTexts()){

                    ShowProgress();
                    loginPresenter.requestLogin(phone.getText().toString() , pass.getText().toString());
                }
            }
        });
    }


    private boolean getTexts(){

        if (TextUtils.isEmpty(phone.getText())){

            phone.setError(getResources().getString(R.string.enter_phone));
            return false;
        }

        if (TextUtils.isEmpty(pass.getText())){

            pass.setError(getResources().getString(R.string.enter_pass));
            return false;
        }

        return true;
    }



    private void showDialogStoreOrBranch(final String store_id , final String branch_id , String store_contact){

        final Intent intent = new Intent(LoginActivity.this , MainActivity.class);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        final Button store = new Button(this);
        final Button main_branch = new Button(this);

        store.setBackground(getResources().getDrawable(R.drawable.prim_solid));
        main_branch.setBackground(getResources().getDrawable(R.drawable.prim_solid));

        store.setText(getResources().getString(R.string.store));
        store.setTextColor(Color.WHITE);
        main_branch.setText(getResources().getString(R.string.main_bra));
        main_branch.setTextColor(Color.WHITE);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(15 , 15 , 15 , 15);

        store.setLayoutParams(lp);
        main_branch.setLayoutParams(lp);

        linearLayout.addView(store);
        linearLayout.addView(main_branch);

        AlertDialog.Builder builder = new AlertDialog.Builder(this , R.style.MyAlertDialogStyle);
        builder.setView(linearLayout);
        builder.show();

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("id" , store_id);
                intent.putExtra("store" , "yes");
                startActivity(intent);
            }
        });

        main_branch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("branch_id" , branch_id);
                tinyDB.putString("id" , store_id);
               // tinyDB.putString("store_contact" , store_contact);
                intent.putExtra("store" , "no");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onViewAdmin(String store_id , String branch_id , String store_contact) {

        showDialogStoreOrBranch(store_id , branch_id , store_contact);
    }

    @Override
    public void onViewBranch(String branch_id , String store_id) {

        tinyDB.putString("branch_id" , branch_id);
        tinyDB.putString("id" , store_id);

        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
        intent.putExtra("store" , "no");
        startActivity(intent);
    }

    @Override
    public void onFailure(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int error) {

        Toast.makeText(this, getResources().getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowProgress() {

        showProgressDialog();
    }

    @Override
    public void HideProgress() {

        hideProgressDialog();
    }
}
