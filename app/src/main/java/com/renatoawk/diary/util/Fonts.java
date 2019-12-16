package com.renatoawk.diary.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.renatoawk.diary.util.Constants;

public class Fonts {

    public static void setTypeFace(Context context, int font, int style, Object...objects){
        Typeface typeface;
        if (font == Constants.FONT_COURIER_PRIME){
            if (style == Constants.FONT_STYLE_REGULAR){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/CourierPrime-Regular.ttf");
                for (Object object : objects) {
                    set(typeface, object);
                }
            } else if (style == Constants.FONT_STYLE_ITALIC){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/CourierPrime-Italic.ttf");
                for (Object object : objects) {
                    set(typeface, object);

                }
            } else if (style == Constants.FONT_STYLE_BOLD){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/CourierPrime-Bold.ttf");
                for (Object object : objects) {
                    set(typeface, object);

                }
            } else if (style == Constants.FONT_STYLE_BOLD_ITALIC){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/CourierPrime-BoldItalic.ttf");
                for (Object object : objects) {
                    set(typeface, object);

                }
            }

        } else if (font == Constants.FONT_ROBOTO){
            if (style == Constants.FONT_STYLE_LIGHT){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                for (Object object : objects) {
                    set(typeface, object);

                }
            } else if (style == Constants.FONT_STYLE_MEDIUM){
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
                for (Object object : objects) {
                    set(typeface, object);

                }
            }

        }
    }

    private static void set(Typeface typeface, Object object) {
        if (object instanceof TextView) {
            ((TextView) object).setTypeface(typeface);
        } else if (object instanceof TextInputLayout) {
            ((TextInputLayout) object).setTypeface(typeface);
        }
    }
}
