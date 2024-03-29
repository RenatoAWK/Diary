package com.renatoawk.diary.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.renatoawk.diary.util.Constants;
import com.renatoawk.diary.encryption.MD5;
import com.renatoawk.diary.util.Fonts;
import com.renatoawk.diary.R;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.model.User;
import com.renatoawk.diary.util.Validation;
import com.renatoawk.diary.util.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText emailEdit, passwordEdit;
    private androidx.appcompat.widget.AppCompatTextView textView;
    private com.google.android.material.button.MaterialButton loginButton, signUpButton;
    private TextInputLayout emailLayout, passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEdit = findViewById(R.id.email_login_edit);
        passwordEdit = findViewById(R.id.password_login_edit);
        emailLayout = findViewById(R.id.email_login_layout);
        passwordLayout = findViewById(R.id.password_login_layout);
        textView = findViewById(R.id.name_login_textview);
        loginButton = findViewById(R.id.login_login_button);
        signUpButton = findViewById(R.id.signup_login_button);


        Fonts.setTypeFace(getApplicationContext(), Constants.FONT_ROBOTO, Constants.FONT_STYLE_MEDIUM,
                textView, loginButton, signUpButton);


        emailEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (Validation.isEmailValid(emailEdit.getText().toString())){
                        emailLayout.setError(null);
                    } else {
                        emailLayout.setError(getText(R.string.invalid_email));
                    }
                }
            }
        });

        passwordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    passwordLayout.setError(null);
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
        boolean emailValid = Validation.isEmailValid(emailEdit, emailLayout, getApplicationContext());
        boolean passwordValid = Validation.isPasswordValid(passwordEdit, passwordLayout, getApplicationContext());
        if (emailValid & passwordValid){
            Map<String, String> map = new HashMap<>();
            map.put(Constants.USER_ATTRIBUTE_EMAIL, emailEdit.getText().toString().trim());
            map.put(Constants.USER_ATTRIBUTE_PASSWORD, MD5.encrypt(passwordEdit.getText().toString()));
            Volley.requestLogin(MainActivity.this, map);
        }

    }

    public void signUp(View view){
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }


}
