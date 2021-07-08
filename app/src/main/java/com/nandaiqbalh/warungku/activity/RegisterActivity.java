package com.nandaiqbalh.warungku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.rest.ApiClient;
import com.nandaiqbalh.warungku.rest.RegisterRequest;
import com.nandaiqbalh.warungku.rest.RegisterResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextInputEditText edtName, edtEmail, edtPhone, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = (TextInputEditText) findViewById(R.id.edt_nama);
        edtEmail = (TextInputEditText) findViewById(R.id.edt_email);
        edtPhone = (TextInputEditText) findViewById(R.id.edt_phone);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password);

        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setName(edtName.getText().toString());
                registerRequest.setEmail(edtEmail.getText().toString());
                registerRequest.setPhoneNumber(edtPhone.getText().toString());
                registerRequest.setPassword(edtPassword.getText().toString());

                register(registerRequest);

            }
        });

    }

    public void register(RegisterRequest registerRequest) {

        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Nama tidak boleh kosong!");
            edtName.requestFocus();
            return;
        } else if (edtEmail.getText().toString().isEmpty()) {
            edtEmail.setError("Email tidak boleh kosong!");
            edtEmail.requestFocus();
            return;
        } else if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("No Telepon tidak boleh kosong!");
            edtPhone.requestFocus();
            return;
        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Password tidak boleh kosong!");
            edtPassword.requestFocus();
            return;
        }

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.isSuccessful()){

                    String message = "Register successfully!";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);


                } else {
                    String message = "An error occured during register. Please try again later!";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}