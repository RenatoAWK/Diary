package com.renatoawk.diary.gui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;

import com.renatoawk.diary.R;

public class ProgressBarDialog {
    private Context context;
    private Dialog dialog;


    public ProgressBarDialog(Context context){
        this.context = context;
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_bar_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        final ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
    }

    public void openDialog(){
        dialog.show();
    }

    public void closeDialog(){
        dialog.dismiss();
    }
}
