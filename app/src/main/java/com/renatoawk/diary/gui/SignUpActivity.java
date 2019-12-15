package com.renatoawk.diary.gui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.renatoawk.diary.Constants;
import com.renatoawk.diary.Encryption;
import com.renatoawk.diary.Fonts;
import com.renatoawk.diary.R;
import com.renatoawk.diary.User;
import com.renatoawk.diary.Validation;
import com.renatoawk.diary.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText emailEdit, passwordEdit, confirmPasswordEdit;
    private AppCompatTextView textView;
    private TextInputLayout emailLayout, passwordLayout, confirmPasswordLayout;
    private MaterialButton signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEdit = findViewById(R.id.email_sign_up_edit);
        passwordEdit = findViewById(R.id.password_sign_up_edit);
        confirmPasswordEdit = findViewById(R.id.password_confirmation_sign_up_edit);
        emailLayout = findViewById(R.id.email_sign_up_layout);
        passwordLayout= findViewById(R.id.password_sign_up_layout);
        confirmPasswordLayout = findViewById(R.id.password_confirmation_sign_up_layout);
        textView = findViewById(R.id.title_sign_up_textview);
        signupButton = findViewById(R.id.sign_up_sign_up_button);

        Fonts.setTypeFace(getApplicationContext(), Constants.FONT_COURIER_PRIME, Constants.FONT_STYLE_REGULAR,
                emailEdit, passwordEdit, confirmPasswordEdit, emailLayout, passwordLayout, confirmPasswordLayout);

        Fonts.setTypeFace(getApplicationContext(), Constants.FONT_COURIER_PRIME, Constants.FONT_STYLE_BOLD,
                textView, signupButton);

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

        confirmPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    confirmPasswordLayout.setError(null);
                }
            }
        });


    }

    public void signUp(View view) {
        boolean emailValid = Validation.isEmailValid(emailEdit, emailLayout, getApplicationContext());
        boolean passwordValid = Validation.isPasswordValid(passwordEdit, passwordLayout, getApplicationContext());
        boolean confirmationPasswordValid = Validation.isPasswordValid(confirmPasswordEdit, confirmPasswordLayout,  getApplicationContext());
        boolean passwordsEqual = Validation.isPasswordsEqual(passwordEdit, passwordLayout, confirmPasswordEdit, confirmPasswordLayout, getApplicationContext());
        if (emailValid && passwordValid && confirmationPasswordValid && passwordsEqual){
            Map<String, String> map = new HashMap<>();
            map.put(Constants.USER_ATTRIBUTE_EMAIL ,emailEdit.getText().toString().trim());
            map.put(Constants.USER_ATTRIBUTE_PASSWORD, Encryption.encrypt(passwordEdit.getText().toString()));
            User user = new User(passwordEdit.getText().toString(), emailEdit.getText().toString().trim());
            Volley.requestSignUp(SignUpActivity.this, map, user);

        }
    }

}
