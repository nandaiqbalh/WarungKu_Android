package com.nandaiqbalh.warungku.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.nandaiqbalh.warungku.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText edtNama, edtEmail, edtPhone, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){
        edtNama = (EditText) findViewById(R.id.edt_nama);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        if (edtNama.getText().toString().isEmpty()){
            edtNama.setError("Nama tidak boleh kosong!");
            edtNama.requestFocus();
            return;
        } else if (edtEmail.getText().toString().isEmpty()){
            edtEmail.setError("Email tidak boleh kosong!");
            edtEmail.requestFocus();
            return;
        } else if (edtPhone.getText().toString().isEmpty()){
            edtPhone.setError("No Telepon tidak boleh kosong!");
            edtPhone.requestFocus();
            return;
        } else if (edtPassword.getText().toString().isEmpty()){
            edtPassword.setError("Password tidak boleh kosong!");
            edtPassword.requestFocus();
            return;
        }

    }
}