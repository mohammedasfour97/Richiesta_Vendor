package com.richiestavendor.Orders.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.richiestavendor.Order;
import com.richiestavendor.Orders.Presenter.OrdersPresenter;
import com.richiestavendor.Orders.Contract;
import com.richiestavendor.R;
import com.richiestavendor.databinding.OrderItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrdersFragment extends Fragment implements Contract.Orders.View {

    private RecyclerView recyclerView;
    private Button add;
    private OrdersAdapter OrdersAdapter;
    private List<Order>OrderList;
    private ProgressDialog progressDialog;
    private OrdersPresenter OrdersPresenter;


    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance(String store_id , String branch_id) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString("store_id", store_id);
        args.putString("branch_id", branch_id);
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

        add.setVisibility(View.GONE);

        OrdersPresenter = new OrdersPresenter(this);

        ShowProgress();
        OrdersPresenter.requestOrders(getArguments().getString("store_id") , getArguments().getString("branch_id"));

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

        OrderList = new ArrayList<>();
        OrdersAdapter = new OrdersAdapter(getContext(), OrderList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setAdapter(OrdersAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onFinished(List<Order> result) {

        OrderList.clear();

        OrderList.addAll(result);

        OrdersAdapter.notifyDataSetChanged();
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


    public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
        private Context context;
        private List<Order> OrderList;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            private OrderItemBinding orderItemBinding;


            public MyViewHolder(OrderItemBinding orderItemBinding) {
                super(orderItemBinding.getRoot());
                this.orderItemBinding = orderItemBinding;

            }
        }


        public OrdersAdapter(Context context, List<Order> OrderList) {
            this.context = context;
            this.OrderList = OrderList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()) ,R.layout.order_item, parent ,
                    false));
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Order Order = OrderList.get(position);

            holder.orderItemBinding.setOrder(Order);

            if (Order.getIsComplete().equals("true")) holder.orderItemBinding.status.setText(
                    getResources().getString(R.string.completed));
            else if (Order.getIsRecieved().equals("true")) holder.orderItemBinding.status.setText(
                    getResources().getString(R.string.recieved));
            else if (Order.getIsCustomerDelivered().equals("true")) holder.orderItemBinding.status.setText(
                    getResources().getString(R.string.cust_delivered));


        }

        @Override
        public int getItemCount() {
            return OrderList.size();
        }


    }
}
