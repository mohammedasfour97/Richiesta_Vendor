package com.richiestavendor.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.richiestavendor.Products.View.ProductsFragment;
import com.richiestavendor.R;
import com.richiestavendor.Settings.Views.SettingsFragment;
import com.richiestavendor.SubSections.View.SubSectionsragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        setListener();

    }

    private void initUI(){

        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void setListener(){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.orders:
                    //    openFragment(HomeFragment.newInstance("", ""));
                        return true;

                    case R.id.products:
                        openFragment(ProductsFragment.newInstance("1'", "0"));
                        return true;

                    case R.id.sub_sections:
                        openFragment(SubSectionsragment.newInstance("1", ""));
                        return true;

                    case R.id.settings:
                        openFragment(SettingsFragment.newInstance());
                        return true;
                }
                return false;
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
