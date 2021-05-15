package com.nandaiqbalh.warungku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.nandaiqbalh.warungku.fragment.AkunFragment;
import com.nandaiqbalh.warungku.fragment.HomeFragment;
import com.nandaiqbalh.warungku.fragment.KeranjangFragment;

public class MainActivity extends AppCompatActivity {

    Fragment homeFragment = new HomeFragment();
    Fragment akunFragment = new AkunFragment();
    Fragment keranjangFragment = new KeranjangFragment();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.container, homeFragment).show(homeFragment).commit();
        fm.beginTransaction().add(R.id.container, akunFragment).hide(akunFragment).commit();
        fm.beginTransaction().add(R.id.container, keranjangFragment).hide(keranjangFragment).commit();
    }
}