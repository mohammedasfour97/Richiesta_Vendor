package com.richiestavendor.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.richiestavendor.Orders.View.OrdersFragment;
import com.richiestavendor.Products.View.ProductsFragment;
import com.richiestavendor.R;
import com.richiestavendor.Settings.Views.SettingsFragment;
import com.richiestavendor.SubSections.View.SubSectionsragment;
import com.richiestavendor.TinyDB;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private boolean is_store;
    private String store_id , branch_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        is_store = getIntent().getStringExtra("store").equals("yes");

        initNavigationMenu();
        setListener();



        branch_id = new TinyDB(this).getString("branch_id");
        store_id = new TinyDB(this).getString("id");

        if (is_store)
            openFragment(OrdersFragment.newInstance(store_id , "-1"));
        else
            openFragment(OrdersFragment.newInstance(store_id , branch_id));

    }

    private void initUI(){

    }

    private void setListener(){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.orders:
                        if (is_store)
                            openFragment(OrdersFragment.newInstance(store_id , "-1"));
                        else
                            openFragment(OrdersFragment.newInstance(store_id , branch_id));
                        return true;

                    case R.id.products:
                        openFragment(ProductsFragment.newInstance(branch_id, "0"));
                        return true;

                    case R.id.settings:
                        openFragment(SettingsFragment.newInstance());
                        return true;
                }
                return false;
            }
        });
    }


    private void initNavigationMenu(){

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if (is_store) bottomNavigationView.inflateMenu(R.menu.store_nav);
        else bottomNavigationView.inflateMenu(R.menu.branch_nav_me);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
