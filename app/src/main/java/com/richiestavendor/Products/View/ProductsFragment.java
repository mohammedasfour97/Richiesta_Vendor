package com.richiestavendor.Products.View;

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

import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.Products.Contract;
import com.richiestavendor.Products.Presenter.ProductsPresenter;
import com.richiestavendor.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductsFragment  extends Fragment implements Contract.Products.View {

    private RecyclerView recyclerView;
    private Button add;
    private ProductsAdapter productsAdapter;
    private List<Product>productList;
    private ProgressDialog progressDialog;
    private ProductsPresenter productsPresenter;


    public ProductsFragment() {
        // Required empty public constructor
    }

    public static ProductsFragment newInstance(String id, String user_id) {
        ProductsFragment fragment = new ProductsFragment();
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

        productsPresenter = new ProductsPresenter(this);

        ShowProgress();
        productsPresenter.requestProducts(getArguments().getString("id") , getArguments().getString("user_id"));

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

        productList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(getContext(), productList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onFinished(List<Product> result) {

        productList.clear();

        productList.addAll(result);

        productsAdapter.notifyDataSetChanged();
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


    public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
        private Context context;
        private List<Product> productList;


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


        public ProductsAdapter(Context context, List<Product> productList) {
            this.context = context;
            this.productList = productList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.product_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Product product = productList.get(position);

            holder.title.setText(product.getARName());


        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


    }
}
