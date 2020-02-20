package com.richiestavendor.Branches.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
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
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BranchesActivity extends BaseActivity implements Contract.Branches.View {

    private RecyclerView recyclerView;
    private Button add;
    private BranchesAdapter BranchesAdapter;
    private List<Branch>BranchList;
    private ProgressDialog progressDialog;
    private BranchesPresenter BranchesPresenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);

        initUI();
        setListeners();
        configureRecyclerView();

        add.setVisibility(View.GONE);

        BranchesPresenter = new BranchesPresenter(this);

        ShowProgress();
        BranchesPresenter.requestBranches("1" , "0");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add , menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onFinished(List<Branch> result) {

        BranchList.clear();

        BranchList.addAll(result);

        BranchesAdapter.notifyDataSetChanged();
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
            private ImageView imageView;


            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                imageView = view.findViewById(R.id.image);
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

            holder.title.setText(Branch.getARName());


        }

        @Override
        public int getItemCount() {
            return BranchList.size();
        }

    }
}