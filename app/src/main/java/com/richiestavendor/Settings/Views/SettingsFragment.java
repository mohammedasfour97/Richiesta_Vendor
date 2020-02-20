package com.richiestavendor.Settings.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.richiestavendor.Branches.View.BranchesActivity;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsFragment extends Fragment {


    private ImageView logo;
    private TextView name;
    private RecyclerView recyclerView;
    private List<String> item_list;
    private SettingAdapter settingAdapter;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view =  inflater.inflate(R.layout.activity_settings, container, false);

        initUI(view);
        setListeners(view);
        configureRecyclerView();

        fillList();

        return view;

    }

    private void initUI(View view){

        logo = view.findViewById(R.id.logo);
        name = view.findViewById(R.id.name);
        recyclerView = view.findViewById(R.id.recyclerview);
    }

    private void setListeners(View view){


    }

    private void configureRecyclerView(){

        item_list =  new ArrayList<>();

        item_list.clear();

        settingAdapter = new SettingAdapter(getContext(), item_list);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
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
        public SettingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.setting_item, parent, false);

            return new SettingAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final SettingAdapter.MyViewHolder holder, final int position) {

            final String setting = itemlist.get(position);

            holder.title.setText(setting);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (position){

                        case 0 :
                            startActivity(new Intent(getContext() , BranchesActivity.class));
                            break;


                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return itemlist.size();
        }

    }
}
