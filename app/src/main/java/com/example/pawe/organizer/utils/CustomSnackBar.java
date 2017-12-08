package com.example.pawe.organizer.utils;

import android.app.Activity;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.pawe.organizer.R;

public class CustomSnackBar {
    public static void makeErrorSnackBar(Activity context, String msg, boolean isAlert) {
        Snackbar snackbar = Snackbar
                .make(context.getWindow().getDecorView().findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View snackbarLayout = snackbar.getView();
        int backgroundColor;
        TextView textView = (TextView)snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(context.getResources().getColor(R.color.black));

        if (isAlert) {
            backgroundColor = ContextCompat.getColor(context.getApplicationContext(), R.color.yellow_alert);
            textView.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_dialog_alert, 0, 0, 0);
        } else {
            backgroundColor = ContextCompat.getColor(context.getApplicationContext(), R.color.green_alert);
            textView.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_dialog_info, 0, 0, 0);
        }
        textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.spacing_normal));
        snackbarLayout.setBackgroundColor(backgroundColor);
        snackbar.show();
    }
}
