package com.renatoawk.diary.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.renatoawk.diary.R;
import com.renatoawk.diary.Validation;

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

    public void login(View view) {
        boolean emailValid = Validation.isEmailValid(emailEdit, getApplicationContext());
        boolean passwordValid = Validation.isPasswordValid(passwordEdit, getApplicationContext());
        if (emailValid & passwordValid){
            ///// login aqui
            Toast.makeText(getApplicationContext(), "Pegou",Toast.LENGTH_LONG).show();
        }

    }

    public void signUp(View view){
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }


}
