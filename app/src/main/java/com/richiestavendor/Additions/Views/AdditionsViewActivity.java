package com.richiestavendor.Additions.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.richiestavendor.Additions.Contract;
import com.richiestavendor.Additions.Presenters.AdditionsPresenter;
import com.richiestavendor.BaseActivity;
import com.richiestavendor.Branches.View.AddBranchFragment;
import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.ModelClasses.Addition;
import com.richiestavendor.R;
import com.richiestavendor.TinyDB;
import com.richiestavendor.View.ViewDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdditionsViewActivity extends BaseActivity implements Contract.Additions.View {

    private RecyclerView recyclerView;
    private Button add;
    private AdditionsAdapter AdditionsAdapter;
    private List<Addition>AdditionList;
    private ProgressDialog progressDialog;
    private com.richiestavendor.Additions.Presenters.AdditionsPresenter AdditionsPresenter;
    private String intent;
    private TinyDB tinyDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);

        initUI();
        setListeners();
        configureRecyclerView();

        add.setVisibility(View.GONE);

        tinyDB = new TinyDB(this);

        AdditionsPresenter = new AdditionsPresenter(this);

        intent = getIntent().getStringExtra("a");

        requestData();

    }

    private void initUI(){

        recyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);
        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    private void setListeners(){


    }

    private void configureRecyclerView(){

        AdditionList = new ArrayList<>();
        AdditionsAdapter = new AdditionsAdapter(this, AdditionList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(AdditionsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    protected void requestData(){

        ShowProgress();

        switch (intent){

            case "color" :
                AdditionsPresenter.requestColors(tinyDB.getString("id"));
                break;

            case "cat" :
                AdditionsPresenter.requestCategories(tinyDB.getString("id"));
                break;

            case "size" :
                AdditionsPresenter.requestSizes(tinyDB.getString("id"));
                break;

            case "addition" :
                AdditionsPresenter.requestAdditions(tinyDB.getString("id"));
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add , menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        FragmentManager fm = getSupportFragmentManager();

        AddAdditionFragment addAdditionFragment = new AddAdditionFragment(intent);
        addAdditionFragment.show(fm, "fragment_new_activity");

        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void onFinished(List<Addition> result, String req) {

        AdditionList.clear();

        AdditionList.addAll(result);

        AdditionsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinished(int m) {

        Toast.makeText(this, getResources().getString(m), Toast.LENGTH_SHORT).show();
        requestData();
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

        progressDialog.show();
    }

    @Override
    public void HideProgress() {

        progressDialog.dismiss();
    }


    public class AdditionsAdapter extends RecyclerView.Adapter<AdditionsAdapter.MyViewHolder> {
        private Context context;
        private List<Addition> AdditionList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Context context;
            private TextView title;
            private ImageView infp , delete;


            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                infp = view.findViewById(R.id.info);
                delete = view.findViewById(R.id.remove);
                context = itemView.getContext();


            }
        }


        public AdditionsAdapter(Context context, List<Addition> AdditionList) {
            this.context = context;
            this.AdditionList = AdditionList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.branch_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Addition Addition = AdditionList.get(position);

            switch (intent){

                case "color" :
                    holder.title.setText(Addition.getClrNameEN());
                    break;

                case "size" :
                    holder.title.setText(Addition.getSizeNameEN());
                    break;

                case "addition" :
                    holder.title.setText(Addition.getAdditional());
                    break;

                case "cat" :
                    holder.title.setText(Addition.getCategoryNameEN());
                    break;
            }

            holder.infp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getSupportFragmentManager();

                    ViewDetails b = null;
                    try {
                        b = new ViewDetails(Constants.toMap(Addition));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    b.show(fm, "fragment_new_activity");

                }
            });


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AdditionsViewActivity.this , R.style.MyAlertDialogStyle);
                    builder.setMessage(getResources().getString(R.string.sure_delete_item));
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ShowProgress();

                            if (Addition.getId()!=null) AdditionsPresenter.requestDeleteAddition(Addition.getId() , intent);
                            else AdditionsPresenter.requestDeleteAddition(Addition.getID() , intent);


                        }
                    });
                    builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builder.show();

                }
            });
        }

        @Override
        public int getItemCount() {
            return AdditionList.size();
        }


    }
}
