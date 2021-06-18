package com.nandaiqbalh.warungku;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nandaiqbalh.warungku.activity.LoginActivity;
import com.nandaiqbalh.warungku.activity.MasukActivity;
import com.nandaiqbalh.warungku.fragment.AkunFragment;
import com.nandaiqbalh.warungku.fragment.HomeFragment;
import com.nandaiqbalh.warungku.fragment.KeranjangFragment;
import com.nandaiqbalh.warungku.helper.SharedPref;

public class MainActivity extends AppCompatActivity {

    private boolean statusLogin = false;
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new HomeFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                            break;
                        case R.id.navigation_keranjang:
                            selectedFragment = new KeranjangFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                            break;
                        case R.id.navigation_akun:
                            if (s.getStatusLogin()){
                                selectedFragment = new AkunFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                            } else {
                                Intent intent = new Intent(MainActivity.this, MasukActivity.class);
                                finish();
                                startActivity(intent);
                            }
                            break;
                        default:
                            return Boolean.parseBoolean(null);

                    }

                    return true;
                }
            };


    SharedPref s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // botton nav bar
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        // default bar-fragment
        Fragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        // shared preferences
        s = new SharedPref(this);
    }
}