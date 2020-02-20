package com.richiestavendor.SubSections.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.richiestavendor.ModelClasses.SubSection;
import com.richiestavendor.R;
import com.richiestavendor.SubSections.Contract;
import com.richiestavendor.SubSections.Presenter.SubSectionsPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SubSectionsragment extends Fragment implements Contract.SubSections.View {

    private RecyclerView recyclerView;
    private Button add;
    private SubSectionsAdapter SubSectionsAdapter;
    private List<SubSection>SubSectionList;
    private ProgressDialog progressDialog;
    private SubSectionsPresenter SubSectionsPresenter;


    public SubSectionsragment() {
        // Required empty public constructor
    }

    public static SubSectionsragment newInstance(String id, String user_id) {
        SubSectionsragment fragment = new SubSectionsragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("user_id", user_id);
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
        View view =  inflater.inflate(R.layout.activity_list_add, container, false);

        initUI(view);
        setListeners(view);
        configureRecyclerView();

        SubSectionsPresenter = new SubSectionsPresenter(this);

        ShowProgress();
        SubSectionsPresenter.requestSubSections(getArguments().getString("id") , getArguments().getString("user_id"));

        return view;

    }

    private void initUI(View view){

        recyclerView = view.findViewById(R.id.recyclerview);
        add = view.findViewById(R.id.add);
        progressDialog = new ProgressDialog(getContext() , R.style.MyAlertDialogStyle);

        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    private void setListeners(View view){


    }

    private void configureRecyclerView(){

        SubSectionList = new ArrayList<>();
        SubSectionsAdapter = new SubSectionsAdapter(getContext(), SubSectionList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(SubSectionsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onFinished(List<SubSection> result) {

        SubSectionList.clear();

        SubSectionList.addAll(result);

        SubSectionsAdapter.notifyDataSetChanged();
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

        progressDialog.dismiss();
    }


    public class SubSectionsAdapter extends RecyclerView.Adapter<SubSectionsAdapter.MyViewHolder> {
        private Context context;
        private List<SubSection> SubSectionList;


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


        public SubSectionsAdapter(Context context, List<SubSection> SubSectionList) {
            this.context = context;
            this.SubSectionList = SubSectionList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sub_section_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final SubSection SubSection = SubSectionList.get(position);

            holder.title.setText(SubSection.getENSubSections());


        }

        @Override
        public int getItemCount() {
            return SubSectionList.size();
        }


    }
}
