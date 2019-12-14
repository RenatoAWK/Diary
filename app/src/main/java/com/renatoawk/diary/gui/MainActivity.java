package com.renatoawk.diary.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.renatoawk.diary.Constants;
import com.renatoawk.diary.Encryption;
import com.renatoawk.diary.Fonts;
import com.renatoawk.diary.R;
import com.renatoawk.diary.Session;
import com.renatoawk.diary.User;
import com.renatoawk.diary.Validation;
import com.renatoawk.diary.Volley;

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

        Fonts.setTypeFace(getApplicationContext(), Constants.FONT_COURIER_PRIME, Constants.FONT_STYLE_REGULAR,
                emailEdit, passwordEdit,emailLayout, passwordLayout);

        Fonts.setTypeFace(getApplicationContext(), Constants.FONT_COURIER_PRIME, Constants.FONT_STYLE_BOLD,
                textView, loginButton, signUpButton);


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
            map.put(Constants.USER_ATTRIBUTE_EMAIL, emailEdit.getText().toString().trim());
            map.put(Constants.USER_ATTRIBUTE_PASSWORD, Encryption.encrypt(passwordEdit.getText().toString()));
            User user = new User(passwordEdit.getText().toString(), emailEdit.getText().toString().trim());
            Volley.requestLogin(MainActivity.this, map, user);
        }

    }

    public void signUp(View view){
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }


}
