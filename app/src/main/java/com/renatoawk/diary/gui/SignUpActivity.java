package com.renatoawk.diary.gui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.renatoawk.diary.R;
import com.renatoawk.diary.Validation;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText emailEdit, passwordEdit, confirmPasswordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){actionBar.hide();}

        emailEdit = findViewById(R.id.email_sign_up_edit);
        passwordEdit = findViewById(R.id.password_sign_up_edit);
        confirmPasswordEdit = findViewById(R.id.password_confirmation_sign_up_edit);

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

        confirmPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    confirmPasswordEdit.setError(null);
                }
            }
        });


    }

    public void signUp(View view) {
        boolean emailValid = Validation.isEmailValid(emailEdit, getApplicationContext());
        boolean passwordValid = Validation.isPasswordValid(passwordEdit, getApplicationContext());
        boolean confirmationPasswordValid = Validation.isPasswordValid(confirmPasswordEdit, getApplicationContext());
        boolean passwordsEqual = Validation.isPasswordsEqual(passwordEdit, confirmPasswordEdit, getApplicationContext());
        if (emailValid && passwordValid && confirmationPasswordValid && passwordsEqual){
            ///// cadastrar aqui
            Toast.makeText(getApplicationContext(),"OKAY",Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View view) {
        finish();
    }
}
