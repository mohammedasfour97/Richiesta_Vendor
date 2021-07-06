package com.richiestavendor.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richiestavendor.ModelClasses.Branch;
import com.richiestavendor.ModelClasses.IDName;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewDetails extends DialogFragment {

    private RecyclerView recyclerView;
    private BranchDetailsAdapter branchDetailsAdapter;
    private List<IDName> idNames;
    private Map<String, Object> toMap;
    
    public ViewDetails(Map<String, Object> toMap) {

        this.toMap = toMap;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.recycler, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        configureRecyclerView();

        return view;

    }

    private void configureRecyclerView(){

        idNames = new ArrayList<>();
        branchDetailsAdapter = new BranchDetailsAdapter(getContext(), idNames);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(branchDetailsAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fillList();
    }


    private void fillList(){

        for (Map.Entry<String, Object> entry : toMap.entrySet()) {

            if (entry.getValue()!=null)
            idNames.add(new IDName(entry.getKey() , entry.getValue().toString()));
        }

        branchDetailsAdapter.notifyDataSetChanged();
    }


    public class BranchDetailsAdapter extends RecyclerView.Adapter<BranchDetailsAdapter.MyViewHolder> {
        private Context context;
        private List<IDName> idNameList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Context context;
            private TextView key , value;


            public MyViewHolder(View view) {
                super(view);
                key = view.findViewById(R.id.key);
                value = view.findViewById(R.id.value);
                context = itemView.getContext();


            }
        }

        public BranchDetailsAdapter(Context context, List<IDName> idNameList) {
            this.context = context;
            this.idNameList = idNameList;
        }

        @Override
        public BranchDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_details, parent, false);

            return new BranchDetailsAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final IDName idName = idNameList.get(position);

            holder.key.setText(idName.getName_ar());
            holder.value.setText(idName.getName_en());


        }

        @Override
        public int getItemCount() {
            return idNameList.size();
        }

    }
}
