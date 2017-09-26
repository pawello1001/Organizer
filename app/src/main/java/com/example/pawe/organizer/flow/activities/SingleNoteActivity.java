package com.example.pawe.organizer.flow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pawe.organizer.R;
import com.example.pawe.organizer.base.activities.BaseActivity;

import butterknife.ButterKnife;

public class SingleNoteActivity extends BaseActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SingleNoteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_note);
        ButterKnife.bind(this);
    }
}
