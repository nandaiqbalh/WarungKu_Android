package com.nandaiqbalh.warungku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nandaiqbalh.warungku.fragment.AkunFragment;
import com.nandaiqbalh.warungku.fragment.HomeFragment;
import com.nandaiqbalh.warungku.fragment.KeranjangFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // botton nav bar
        Fragment homeFragment = new HomeFragment();
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.navigation_keranjang:
                            selectedFragment = new KeranjangFragment();
                            break;
                        case R.id.navigation_akun:
                            selectedFragment = new AkunFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();

                    return true;
                }
            };
}