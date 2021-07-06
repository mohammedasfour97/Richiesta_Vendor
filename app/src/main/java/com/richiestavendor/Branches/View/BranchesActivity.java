package com.richiestavendor.Branches.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.richiestavendor.BaseActivity;
import com.richiestavendor.Branches.Contract;
import com.richiestavendor.Branches.Presenter.BranchesPresenter;
import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.R;
import com.richiestavendor.View.ViewDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BranchesActivity extends BaseActivity implements Contract.Branches.View {

    private RecyclerView recyclerView;
    private Button add;
    private BranchesAdapter BranchesAdapter;
    private List<Branch>BranchList;
    private ProgressDialog progressDialog;
    private BranchesPresenter BranchesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);

        initUI();
        setListeners();
        configureRecyclerView();

        add.setVisibility(View.GONE);

        BranchesPresenter = new BranchesPresenter(this);

        getBranches();
    }

    private void initUI(){

        recyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);
        progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    private void setListeners(){


    }

    private void configureRecyclerView(){

        BranchList = new ArrayList<>();
        BranchesAdapter = new BranchesAdapter(this, BranchList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(BranchesAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    public void getBranches(){

        ShowProgress();
        BranchesPresenter.requestBranches("0" );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add , menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        FragmentManager fm = getSupportFragmentManager();

        AddBranchFragment addBranchFragment = AddBranchFragment.newInstance();
        addBranchFragment.show(fm, "fragment_new_activity");

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onFinished(List<Branch> result) {

        BranchList.clear();

        BranchList.addAll(result);

        BranchesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinished() {

        Toast.makeText(this, getResources().getString(R.string.succe_delete_bra) ,  Toast.LENGTH_SHORT).show();

        ShowProgress();
        BranchesPresenter.requestBranches("0" );
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




    public class BranchesAdapter extends RecyclerView.Adapter<BranchesAdapter.MyViewHolder> {
        private Context context;
        private List<Branch> BranchList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Context context;
            private TextView title;
            private ImageView info , delete , edit;


            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                info = view.findViewById(R.id.info);
                delete = view.findViewById(R.id.remove);
                edit = view.findViewById(R.id.edit);
                context = itemView.getContext();


            }
        }


        public BranchesAdapter(Context context, List<Branch> BranchList) {
            this.context = context;
            this.BranchList = BranchList;
        }

        @Override
        public BranchesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.branch_item, parent, false);

            return new BranchesAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Branch Branch = BranchList.get(position);

            holder.title.setText(Branch.getNameAR());


            holder.info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getSupportFragmentManager();

                    ViewDetails b = null;
                    try {
                        b = new ViewDetails(Constants.toMap(Branch));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    b.show(fm, "fragment_new_activity");

                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(BranchesActivity.this , R.style.MyAlertDialogStyle);
                    builder.setMessage(getResources().getString(R.string.sure_delete_bra));
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ShowProgress();

                            BranchesPresenter.requestDeleteBranch(Branch.getRK_Branch_ID() , Branch.getRKBranchDetails_ID());
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

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getSupportFragmentManager();

                    AddBranchFragment addBranchFragment = new AddBranchFragment(Branch);
                    addBranchFragment.show(fm, "fragment_new_activity");
                }
            });
        }

        @Override
        public int getItemCount() {
            return BranchList.size();
        }

    }
}