package com.richiestavendor.Settings.Views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.richiestavendor.Additions.Views.AdditionsViewActivity;
import com.richiestavendor.Branches.View.BranchesActivity;
import com.richiestavendor.Constants;
import com.richiestavendor.R;
import com.richiestavendor.Settings.Contract;
import com.richiestavendor.Settings.Presenters.SettingsPresenter;
import com.richiestavendor.Store;
import com.richiestavendor.TinyDB;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsFragment extends Fragment implements Contract.Settings.View {


    private ImageView logo;
    private TextView name;
    private RecyclerView recyclerView;
    private List<String> item_list;
    private SettingAdapter settingAdapter;
    private SettingsPresenter settingsPresenter;
    private ProgressDialog progressDialog;

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

        settingsPresenter = new SettingsPresenter(this);

        setData();

        configureRecyclerView();

        fillList();

        return view;

    }

    private void initUI(View view){

        logo = view.findViewById(R.id.logo);
        name = view.findViewById(R.id.name);
        recyclerView = view.findViewById(R.id.recyclerview);

        progressDialog = new ProgressDialog(getContext() , R.style.MyAlertDialogStyle);

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    private void setData(){

        ShowProgress();
        settingsPresenter.requestStoreData();

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

        item_list.add(getResources().getString(R.string.branches));
        item_list.add(getResources().getString(R.string.users));
        item_list.add(getResources().getString(R.string.categories));
        item_list.add(getResources().getString(R.string.sizes));
        item_list.add(getResources().getString(R.string.colors));
        item_list.add(getResources().getString(R.string.additional));
        item_list.add(getResources().getString(R.string.exit));

        settingAdapter.notifyDataSetChanged();
    }

    private void setDataInTextViews(Store store){

        Glide.with(getContext()).load(Constants.IMAGE_URL+(store.getRK_store_logo().substring(9))).into(logo);

        name.setText(store.getRK_store_nameAr());
    }

    @Override
    public void onFinished(Store result) {

        setDataInTextViews(result);
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

        progressDialog.show();
    }

    @Override
    public void HideProgress() {

        progressDialog.hide();
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

                    Intent intent;

                    switch (position){

                        case 0 :
                            startActivity(new Intent(getContext() , BranchesActivity.class));
                            break;

                        case 2 :
                            intent = new Intent(getContext() , AdditionsViewActivity.class);
                            intent.putExtra("a" , "cat");
                            startActivity(intent);
                            break;

                        case 3 :
                            intent = new Intent(getContext() , AdditionsViewActivity.class);
                            intent.putExtra("a" , "size");
                            startActivity(intent);
                            break;

                        case 4 :
                            intent = new Intent(getContext() , AdditionsViewActivity.class);
                            intent.putExtra("a" , "color");
                            startActivity(intent);
                            break;

                        case 5 :
                            intent = new Intent(getContext() , AdditionsViewActivity.class);
                            intent.putExtra("a" , "addition");
                            startActivity(intent);
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
