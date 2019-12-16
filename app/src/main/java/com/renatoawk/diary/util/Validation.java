package com.renatoawk.diary.util;

import android.content.Context;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.renatoawk.diary.R;

public class Validation {
    public static boolean isEmailValid(String text) {
        return !isBlank(text) && Patterns.EMAIL_ADDRESS.matcher(text.trim()).matches();

    }

    public static boolean isBlank(String text) {
        return text.replace(" ", "").replace("\n", "").replace("\r", "").isEmpty();
    }

    public static boolean isEmailValid(TextInputEditText textInputEditText, TextInputLayout textInputLayout, Context context) {
        if (Validation.isBlank(textInputEditText.getText().toString().trim())) {
            textInputLayout.setError(context.getString(R.string.empty_field));
            return false;
        } else {
            if (Validation.isEmailValid(textInputEditText.getText().toString())) {
                textInputLayout.setError(null);
                return true;
            }
            textInputLayout.setError(context.getText(R.string.invalid_email));
            return false;

        }
    }

    public static boolean isPasswordValid(TextInputEditText textInputEditText, TextInputLayout textInputLayout, Context context){
        if (isBlank(textInputEditText.getText().toString())){
            textInputLayout.setError(context.getString(R.string.empty_field));
            return false;
        } else if (textInputEditText.getText().toString().length() > 32){
            textInputLayout.setError(context.getString(R.string.password_must_be_32_characters_or_less));
            return false;
        }
        textInputLayout.setError(null);
        return true;
    }

    public static boolean isPasswordsEqual(TextInputEditText passwordEdit, TextInputLayout passwordLayout,
                                           TextInputEditText confirmPasswordEdit, TextInputLayout confirmPasswordLayout,
                                           Context context) {
        if(passwordEdit.getText().toString().equals(confirmPasswordEdit.getText().toString())){
          boolean passwordValid = isPasswordValid(passwordEdit, passwordLayout, context);
          boolean confirmationPasswordValid = isPasswordValid(confirmPasswordEdit, confirmPasswordLayout, context);
          if ( passwordValid && confirmationPasswordValid){
              passwordLayout.setError(null);
              confirmPasswordLayout.setError(null);
              
              return true;
          }
          return false;

        } else {
            passwordLayout.setError(context.getString(R.string.password_different));
            confirmPasswordLayout.setError(context.getString(R.string.password_different));
            return  false;
        }
    }
}
