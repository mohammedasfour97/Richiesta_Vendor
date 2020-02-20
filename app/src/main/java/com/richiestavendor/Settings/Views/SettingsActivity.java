package com.richiestavendor.Settings.Views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.richiestavendor.BaseActivity;
import com.richiestavendor.Branches.View.BranchesActivity;
import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsActivity extends BaseActivity {

    private ImageView logo;
    private TextView name;
    private RecyclerView recyclerView;
    private List<String> item_list;
    private SettingAdapter settingAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initUI();
        setListeners();

        item_list = new ArrayList<>();

        configureRecyclerView();

        fillList();
    }

    private void initUI(){

        logo = findViewById(R.id.logo);
        name = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recyclerview);
    }

    private void setListeners(){


    }

    private void configureRecyclerView(){

        item_list.clear();

        settingAdapter = new SettingAdapter(this, item_list);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(settingAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void fillList(){

        item_list.clear();

        item_list.add("Branches");
        item_list.add("Users");
        item_list.add("Tags");
        item_list.add("Sizes");
        item_list.add("Colors");
        item_list.add("Additional");
        item_list.add("Exit");

        settingAdapter.notifyDataSetChanged();
    }

    public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.MyViewHolder> {
        private Context context;
        private List<String> itemlist;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Context context;
            private TextView title;


            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                context = itemView.getContext();


            }
        }


        public SettingAdapter(Context context, List<String> itemlist) {
            this.context = context;
            this.itemlist = itemlist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.branch_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final String setting = itemlist.get(position);

            holder.title.setText(setting);


        }

        @Override
        public int getItemCount() {
            return itemlist.size();
        }

    }
}
