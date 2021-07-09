package com.nandaiqbalh.warungku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.nandaiqbalh.warungku.MainActivity;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.helper.SharedPref;
import com.nandaiqbalh.warungku.rest.ApiClient;
import com.nandaiqbalh.warungku.rest.RegisterRequest;
import com.nandaiqbalh.warungku.rest.RegisterResponse;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private TextInputEditText edtName, edtEmail, edtPhone, edtPassword;
    SharedPref s;
    ProgressBar progressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // shared preferences
        s = new SharedPref(this);

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

    private void register(RegisterRequest registerRequest) {

        String emailInput = edtEmail.getText().toString().trim(); // untuk validasi email
        int phoneInput = edtPhone.getText().length(); // untuk validasi nomor telepon
        String passwordInput = edtPassword.getText().toString().trim(); // untuk validasi password
        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        // "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$");

        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Nama tidak boleh kosong!");
            edtName.requestFocus();
            return;
        } else if (emailInput.isEmpty()) {
            edtEmail.setError("Email tidak boleh kosong!");
            edtEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            edtEmail.setError("Email yang anda masukkan tidak valid!");
            edtEmail.requestFocus();
            return;
        } else if (edtPhone.getText().toString().isEmpty()) {
            edtPhone.setError("No Telepon tidak boleh kosong!");
            edtPhone.requestFocus();
            return;
        } else if (phoneInput < 10 || phoneInput > 13) {
            edtPhone.setError("No Telepon tidak valid!");
            edtPhone.requestFocus();
            return;
        } else if (passwordInput.isEmpty()) {
            edtPassword.setError("Password tidak boleh kosong!");
            edtPassword.requestFocus();
            return;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            edtPassword.setError("Password anda terlalu lemah! (Minimal 8 digit, angka + huruf).");
            edtPassword.requestFocus();
            return;
        }

        progressBarRegister = (ProgressBar) findViewById(R.id.pb_register);
        progressBarRegister.setVisibility(View.VISIBLE);

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressBarRegister.setVisibility(View.GONE);

                RegisterResponse respon = response.body();

                if (respon.getSuccess() == 1){
                    // berhasil
                    Toast.makeText(RegisterActivity.this, "Success : " + respon.getMessage(), Toast.LENGTH_LONG).show();
                    s.setStatusLogin(true);
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    // gagal
                    Toast.makeText(RegisterActivity.this, "Error : " + respon.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressBarRegister.setVisibility(View.GONE);

                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}