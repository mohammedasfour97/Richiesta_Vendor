package com.richiestavendor.Products.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.richiestavendor.Constants;
import com.richiestavendor.ModelClasses.Product;
import com.richiestavendor.Products.Contract;
import com.richiestavendor.Products.Presenter.ProductsPresenter;
import com.richiestavendor.R;
import com.richiestavendor.View.ViewDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

        requestProducts();

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext() , AddProductActivity.class);
                intent.putExtra("edit" , "no");
                startActivity(intent);
            }
        });
    }

    private void configureRecyclerView(){

        productList = new ArrayList<>();
        productsAdapter = new ProductsAdapter(getContext(), productList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    private void requestProducts(){

        ShowProgress();
        productsPresenter.requestProducts(getArguments().getString("id") , getArguments().getString("user_id"));
    }



    @Override
    public void onFinished(List result) {

        productList.clear();

        productList.addAll(result);

        productsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinished(String result) {
        //////////////////
    }

    @Override
    public void onFinished(int result) {

        Toast.makeText(getContext(), getResources().getString(result), Toast.LENGTH_SHORT).show();

        requestProducts();
    }

    @Override
    public void onAddImgFinished() {

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
            private ImageView edit , remove , info , logo;


            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                logo = view.findViewById(R.id.imageView);
                info = view.findViewById(R.id.info);
                edit = view.findViewById(R.id.edit);
                remove = view.findViewById(R.id.remove);
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

            holder.title.setText(product.getRKPrdNameAR());
            Glide.with(holder.context).load(Constants.IMAGE_URL+(product.getProductPic().substring(9))).into(holder.logo);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = getActivity().getSupportFragmentManager();

                    ViewDetails b = null;
                    try {
                        b = new ViewDetails(Constants.toMap(product));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    b.show(fm, "fragment_new_activity");

                }
            });

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext() , R.style.MyAlertDialogStyle);
                    builder.setMessage(getResources().getString(R.string.sure_delete_pro));
                    builder.setCancelable(false);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ShowProgress();
                            productsPresenter.getrequestDeleteProducts(product.getId());
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

                    Intent intent = new Intent(getContext() , AddProductActivity.class);
                    intent.putExtra("edit" , "yes");
                    intent.putExtra("p" , product);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }


    }
}
