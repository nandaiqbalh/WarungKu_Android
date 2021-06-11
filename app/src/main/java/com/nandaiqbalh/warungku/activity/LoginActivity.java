package com.nandaiqbalh.warungku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.helper.SharedPref;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    SharedPref s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.btn_login);

        // shared preferences
        s = new SharedPref(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.setStatusLogin(true);
            }
        });
    }
}