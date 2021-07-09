package com.nandaiqbalh.warungku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nandaiqbalh.warungku.MainActivity;
import com.nandaiqbalh.warungku.R;
import com.nandaiqbalh.warungku.fragment.AkunFragment;
import com.nandaiqbalh.warungku.helper.SharedPref;
import com.nandaiqbalh.warungku.rest.ApiClient;
import com.nandaiqbalh.warungku.rest.LoginRequest;
import com.nandaiqbalh.warungku.rest.LoginResponse;
import com.nandaiqbalh.warungku.rest.RegisterRequest;
import com.nandaiqbalh.warungku.rest.RegisterResponse;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtEmail, edtPassword;
    private Button loginButton;
    SharedPref s;
    ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // shared preferences
        s = new SharedPref(this);

        loginButton = (Button) findViewById(R.id.btn_login);

        edtEmail = (TextInputEditText) findViewById(R.id.edt_email);
        edtPassword = (TextInputEditText) findViewById(R.id.edt_password);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(edtEmail.getText().toString());
                loginRequest.setPassword(edtPassword.getText().toString());
                login(loginRequest);
            }
        });
    }

    private void login(LoginRequest loginRequest) {

        String emailInput = edtEmail.getText().toString().trim(); // untuk validasi email
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


        if (emailInput.isEmpty()) {
            edtEmail.setError("Email tidak boleh kosong!");
            edtEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            edtEmail.setError("Email yang anda masukkan tidak valid!");
            edtEmail.requestFocus();
            return;
        } else if (passwordInput.isEmpty()) {
            edtPassword.setError("Password tidak boleh kosong!");
            edtPassword.requestFocus();
            return;
        }

        progressBarLogin = (ProgressBar) findViewById(R.id.pb_login);
        progressBarLogin.setVisibility(View.VISIBLE);

        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBarLogin.setVisibility(View.GONE);
                LoginResponse respon = response.body();

                if (respon.getSuccess() == 1){
                    // berhasil
                    Toast.makeText(LoginActivity.this, "Success : " + respon.getMessage(), Toast.LENGTH_LONG).show();
                    s.setStatusLogin(true);
                    s.setString(s.name, respon.getUser().getName());
                    s.setString(s.phone, respon.getUser().getPhone());
                    s.setString(s.email, respon.getUser().getEmail());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    // gagal
                    Toast.makeText(LoginActivity.this, "Error : " + respon.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBarLogin.setVisibility(View.GONE);

                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}