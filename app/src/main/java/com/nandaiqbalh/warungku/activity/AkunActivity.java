package com.nandaiqbalh.warungku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.helper.SharedPref;

public class AkunActivity extends AppCompatActivity {

    // deklarasi member variabel
    private Button btnLogin;
    private Button btnDaftar;
    SharedPref s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun);

        // shared preferences
        s = new SharedPref(this);
        mainButton();

    }

    private void mainButton() {

        // inisiasi button
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnDaftar = (Button) findViewById(R.id.btn_daftar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setStatusLogin(true);
                Intent intent = new Intent(AkunActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AkunActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}