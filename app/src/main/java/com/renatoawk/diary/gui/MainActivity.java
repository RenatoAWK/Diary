package com.renatoawk.diary.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.renatoawk.diary.R;
import com.renatoawk.diary.Session;
import com.renatoawk.diary.User;
import com.renatoawk.diary.Validation;
import com.renatoawk.diary.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText emailEdit, passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){actionBar.hide();}

        emailEdit = findViewById(R.id.email_login_edit);
        passwordEdit = findViewById(R.id.password_login_edit);

        emailEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (Validation.isEmailValid(emailEdit.getText().toString())){
                        emailEdit.setError(null);
                    } else {
                        emailEdit.setError(getText(R.string.invalid_email));
                    }
                }
            }
        });

        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    passwordEdit.setError(null);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Session.user != null){
            emailEdit.setText(Session.user.getEmail());
        }
    }

    public void login(View view) {
        boolean emailValid = Validation.isEmailValid(emailEdit, getApplicationContext());
        boolean passwordValid = Validation.isPasswordValid(passwordEdit, getApplicationContext());
        if (emailValid & passwordValid){
            Map<String, String> map = new HashMap<>();
            map.put("email",emailEdit.getText().toString().trim());
            map.put("password",passwordEdit.getText().toString());
            map.put("type","login");
            User user = new User(passwordEdit.getText().toString(), emailEdit.getText().toString().trim());
            Volley.requestLogin(MainActivity.this, map, user);
        }

    }

    public void signUp(View view){
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }


}