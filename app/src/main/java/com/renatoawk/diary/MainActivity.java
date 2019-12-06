package com.renatoawk.diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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
                    if (!emailEdit.getText().toString().replace(" ","").isEmpty()){
                        if (Patterns.EMAIL_ADDRESS.matcher(emailEdit.getText().toString().trim()).matches())
                            emailEdit.setError(null);
                        else {
                            emailEdit.setError(getText(R.string.invalid_email));
                        }
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
        boolean emailValid = isEmailValid();
        boolean passwordValid = isPasswordValid();
        if (emailValid & passwordValid){
            Toast.makeText(getApplicationContext(), "Pegou",Toast.LENGTH_LONG).show();
            // Continuar o login
        }

    }

    private boolean isEmailValid() {
        if (emailEdit.getText().toString().replace(" ","").isEmpty()){
            emailEdit.setError(getString(R.string.empty_field));
            return false;
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(emailEdit.getText().toString().trim()).matches()){
                emailEdit.setError(null);
                return true;
            }
            emailEdit.setError(getText(R.string.invalid_email));
            return false;
        }
    }

    private boolean isPasswordValid() {
        if (passwordEdit.getText().toString().replace(" ", "").isEmpty()) {
            passwordEdit.setError(getString(R.string.empty_field));
            return false;
        }
        return true;
    }


}
