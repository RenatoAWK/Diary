package com.renatoawk.diary;

import android.content.Context;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;

public class Validation {
    public static boolean isEmailValid(String text) {
        return !isBlank(text) && Patterns.EMAIL_ADDRESS.matcher(text.trim()).matches();

    }

    public static boolean isBlank(String text) {
        return text.replace(" ", "").replace("\n", "").replace("\r", "").isEmpty();
    }

    public static boolean isEmailValid(TextInputEditText textInputEditText, Context context) {
        if (Validation.isBlank(textInputEditText.getText().toString().trim())) {
            textInputEditText.setError(context.getString(R.string.empty_field));
            return false;
        } else {
            if (Validation.isEmailValid(textInputEditText.getText().toString())) {
                textInputEditText.setError(null);
                return true;
            }
            textInputEditText.setError(context.getText(R.string.invalid_email));
            return false;

        }
    }

    public static boolean isPasswordValid(TextInputEditText textInputEditText, Context context){
        if (isBlank(textInputEditText.getText().toString())){
            textInputEditText.setError(context.getString(R.string.empty_field));
            return false;
        }
        textInputEditText.setError(null);
        return true;
    }

    public static boolean isPasswordsEqual(TextInputEditText passwordEdit, TextInputEditText confirmPasswordEdit, Context context) {
        if(passwordEdit.getText().toString().equals(confirmPasswordEdit.getText().toString())){
          boolean passwordValid = isPasswordValid(passwordEdit, context);
          boolean confirmationpasswordValid = isPasswordValid(confirmPasswordEdit, context);
          if ( passwordValid && confirmationpasswordValid){
              passwordEdit.setError(null);
              confirmPasswordEdit.setError(null);
              return true;
          }
          return false;

        } else {
            passwordEdit.setError(context.getString(R.string.password_different));
            confirmPasswordEdit.setError(context.getString(R.string.password_different));
            return  false;
        }
    }
}